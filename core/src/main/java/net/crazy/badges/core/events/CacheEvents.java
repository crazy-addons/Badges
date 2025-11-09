package net.crazy.badges.core.events;

import net.crazy.badges.core.BadgesAddon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.playerinfo.PlayerInfoRemoveEvent;
import net.labymod.api.event.client.network.server.ServerDisconnectEvent;

public class CacheEvents {
  private final BadgesAddon addon;

  public CacheEvents(BadgesAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onPlayerDisconnect(PlayerInfoRemoveEvent event) {
    addon.playerCache.remove(event.playerInfo().profile().getUniqueId());
  }

  @Subscribe
  public void onClientDisconnect(ServerDisconnectEvent event) {
    addon.playerCache.clear();
  }
}
