// Data Access Classes
package com.example.geniusapp.dao;

import com.example.geniusapp.core.user.Account;
import com.example.geniusapp.core.user.Artist;
import com.example.geniusapp.content.Album;
import com.example.geniusapp.content.Song;
import com.example.geniusapp.util.Platform;

import java.util.List;
import java.util.stream.Collectors;

public class AccountDAO {
    private Platform platform = Platform.getInstance();

    public void save(Account account) {
        if (!platform.getAllAccounts().contains(account)) {
            platform.getAllAccounts().add(account);
            if (account instanceof Artist && !platform.getAllArtists().contains(account)) {
                platform.getAllArtists().add((Artist) account);
            }
            System.out.println("Account saved: " + account.getUsername());
        } else {
            System.out.println("Account already exists: " + account.getUsername());
        }
    }

    public void update(Account account) {
        // Basic in-memory update
        for (int i = 0; i < platform.getAllUsers().size(); i++) {
            if (platform.getAllAccounts().get(i).getId() == account.getId()) {
                platform.getAllAccounts().set(i, account);
                System.out.println("Account updated: " + account.getUsername());
                return;
            }
        }
        System.out.println("Account not found for update: " + account.getUsername());
    }

    public void delete(Account account) {
        platform.getAllAccounts().remove(account);
        if (account instanceof Artist) {
            platform.getAllArtists().remove(account);
        }
        System.out.println("Account deleted: " + account.getUsername());
    }

    public Account findById(int id) {
        return platform.getAllAccounts().stream().filter(account -> account.getId() == id).findFirst().orElse(null);
    }

    public Account findByUsername(String username) {
        return platform.getAllAccounts().stream().filter(account -> account.getUsername().equals(username)).findFirst().orElse(null);
    }
}