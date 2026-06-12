package org.ginjol;

/*
in this project, you will build a simple command line interface (CLI) to fetch the recent activity of a GitHub user and
display it in the terminal.
This project will help you practice your programming skills, including working with APIs, handling JSON data,
and building a simple CLI application.

https://roadmap.sh/projects/github-user-activity

 */

import org.ginjol.githubactivity.ConnectionGithub;
import org.ginjol.githubactivity.models.GitHubData;
import org.ginjol.githubactivity.utils.Printer;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String userResponse = args.length > 0 ? args[0] : new Scanner(System.in).next();

        ConnectionGithub connection = new ConnectionGithub(userResponse);
        ArrayList<GitHubData> listGitHubData = connection.connect();

        Printer printer = new Printer();

        printer.getGithubUserData(listGitHubData.getFirst());
        printer.getActivity(listGitHubData);

    }

}

