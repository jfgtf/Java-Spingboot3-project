package com.springboot.githubapi;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class GithubApiController {
    private static final String BASE_URL =  "https://api.github.com";

    @GetMapping("/repositories/{username}")
    public ResponseEntity<?> getRepositories(
            @PathVariable("username") String username,
            @RequestHeader("Accept") String acceptHeader
    ) {

        if (!acceptHeader.equals("application/json")) {
            boolean isXML = false;
            if (acceptHeader.equals("application/xml")){
                isXML = true;
            }

            String error = "Unsupported media type";
            return buildResponseEntity(new ApiError(HttpStatus.NOT_ACCEPTABLE, error), isXML);
        }

        try {
            List<Repository> repositories = fetchRepositories(username);
            return ResponseEntity.ok(repositories);
        } catch (HttpClientErrorException.NotFound ex) {
            String error = "User not found";
            return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, error), false);
        }
    }

    private List<Repository> fetchRepositories(String username) {
        String url = BASE_URL + "/users/" + username + "/repos";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new RequestInterceptor());
        Repository[] repositories = restTemplate.getForObject(url, Repository[].class);

        List<Repository> filteredRepositories = filterForks(Arrays.asList(repositories));
        filteredRepositories.forEach(repo -> {
            repo.setOwnerLogin(username);

            String branchesUrl = BASE_URL + "/repos/" + username + "/" + repo.getName() + "/branches";
            Branch[] branches = restTemplate.getForObject(branchesUrl, Branch[].class);
            repo.setBranches(Arrays.asList(branches));
        });

        return filteredRepositories;
    }


    public List<Repository> filterForks(List<Repository> repositories) {
        return repositories.stream().filter(repository -> !repository.getFork()).collect(Collectors.toList());
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError, boolean isXML) {
        if (isXML==true) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            return new ResponseEntity<>("{\n \t\"status\": \"NOT_ACCEPTABLE\", \n \t\"message\": \"Unsupported media type\" \n}", headers, apiError.getStatus());
        }
        else{
            return new ResponseEntity<>(apiError, apiError.getStatus());
        }
    }
}
