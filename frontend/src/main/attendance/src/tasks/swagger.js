var gulp = require("gulp");
var yaml = require("js-yaml");
var path = require("path");
var fs = require("fs");

var syncSwagger = function () {
  var doc = yaml.safeLoad(fs.readFileSync(path.join(__dirname, "../api/swagger/swagger.yaml")));
  fs.writeFileSync(
    "/var/local/hw-spring/swagger.json",
    JSON.stringify(doc, null, " ")
  )
};
gulp.task("swagger", syncSwagger);
