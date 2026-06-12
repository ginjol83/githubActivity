package org.ginjol.githubactivity.utils;

import org.ginjol.githubactivity.models.GitHubData;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

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
    }

    public void getActivity(ArrayList<GitHubData> listGitHubData) {
        Map<String, Integer> counters = new HashMap<>();

        for (GitHubData gitHubData : listGitHubData) {
            if (gitHubData == null || gitHubData.getType() == null) continue;
            counters.merge(gitHubData.getType(), 1, Integer::sum);
        }
        System.out.println(ansi().fgMagenta().a("----------------------").reset());
        System.out.println(ansi().fgMagenta().a("Github last Activity: ").reset());
        System.out.println(ansi().fgMagenta().a("----------------------").reset());

        counters.forEach((type, count) -> System.out.println(ansi().fgMagenta().a(type + ": " + count).reset()));
    }
}
