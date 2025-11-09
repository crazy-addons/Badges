package net.crazy.badges.core.listener;

import net.crazy.badges.core.BadgesAddon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.playerinfo.PlayerInfoRemoveEvent;
import net.labymod.api.event.client.network.server.ServerDisconnectEvent;

public class PlayerListener {

  @Subscribe
  public void onPlayerDisconnect(PlayerInfoRemoveEvent event) {
    BadgesAddon.badgeManager().removeFromPlayerCache(event.playerInfo().profile().getUniqueId());
  }

  @Subscribe
  public void onClientDisconnect(ServerDisconnectEvent event) {
    BadgesAddon.badgeManager().clearPlayerCache();
  }
}
