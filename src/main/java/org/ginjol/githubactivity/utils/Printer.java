package org.ginjol.githubactivity.utils;

import org.ginjol.githubactivity.models.GitHubData;

import static org.fusesource.jansi.Ansi.ansi;

public class Printer {

    public void getGithubUserData(GitHubData gitHubData){
        System.out.flush();
        System.out.println(ansi().fgMagenta().a("Github user data: ").reset());
        System.out.println(ansi().fgMagenta().a("------------------").reset());

        System.out.print(ansi().fgMagenta().a("User Id: ").reset());
        System.out.print(ansi().fgGreen().a(gitHubData.getActor().getId()).reset()+"\n");

        System.out.print(ansi().fgMagenta().a("Login: ").reset());
        System.out.print(ansi().fgGreen().a(gitHubData.getActor().getLogin()).reset()+"\n");

        System.out.print(ansi().fgMagenta().a("Url: ").reset());
        System.out.print(ansi().fgGreen().a(gitHubData.getActor().getUrl()).reset()+"\n");

        System.out.print(ansi().fgMagenta().a("Avatar: ").reset());
        System.out.print(ansi().fgGreen().a(gitHubData.getActor().getAvatar_url()).reset()+"\n");

        //System.out.println("Github user data: "+ansi().fgGreen().a(gitHubData.getActor().getGravatar_id()).reset());
        //System.out.println("Github user data: "+ansi().fgGreen().a(gitHubData.getActor().getDisplay_login()).reset());
    }
}
