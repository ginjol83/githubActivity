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

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Main {
    public static void main(String[] args) {
        System.out.println(ansi().fgGreen().a("Intro Username!").reset());

        Scanner sc = new Scanner(System.in);
        String  userResponse = sc.next();



        ConnectionGithub connection = new ConnectionGithub(userResponse);
        GitHubData       gitHubData = connection.connect();

        Printer printer = new Printer();
        printer.getGithubUserData(gitHubData);
    }

}

