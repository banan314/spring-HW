# stage 1
FROM node as frontend
WORKDIR /frontend
COPY frontend/attendance .
RUN git config --global url."https://".insteadOf ssh://
RUN npm install
RUN npm run build

# stage 2
FROM adoptopenjdk/openjdk15:latest as backend
WORKDIR /backend
COPY backend .
RUN mkdir -p src/main/resources/static
COPY --from=frontend /frontend/dist/attendance src/main/resources/static
RUN apt-get update
RUN apt-get -y install maven
RUN mvn package
ARG PORT=8080
EXPOSE ${PORT}
CMD ["sh", "-c", "java -Dport ${PORT} -jar target/backend-0.5.jar"]
