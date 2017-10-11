package hw.spring.controllers;

import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.social.github.api.*;

import java.util.List;

@RestController
@RequestMapping(path = "/github")
public class GithubController {

    RepoOperations repoOperations = assembleRepoOperations();

    @GetMapping(value = "commits")
    public List<GitHubCommit> getCommits() {
        String userName = "banan314";
        String repoName = "spring-HW";

        return repoOperations.getCommits(userName, repoName);
    }

    RepoOperations assembleRepoOperations() {
        GitHubTemplate gitHub = new GitHubTemplate("ACCESS_TOKEN");
        return gitHub.repoOperations();
    }
}