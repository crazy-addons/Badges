package net.crazy.badges.core.events;

import java.util.LinkedList;
import java.util.UUID;
import net.crazy.badges.core.BadgesAddon;
import net.crazy.badges.core.badges.Badge;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.render.model.entity.player.PlayerModelRenderEvent;

public class PlayerRenderEvent {

  private final BadgesAddon addon;

  public PlayerRenderEvent(BadgesAddon addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onPlayerRender(PlayerModelRenderEvent event) {
    Player player = event.player();
    UUID uuid = player.getUniqueId();

    if (!addon.playerCache.containsKey(uuid)) {
      addon.playerCache.put(uuid, getUserBadges(uuid));
    }
  }

  private LinkedList<Badge> getUserBadges(UUID uuid) {
    LinkedList<Badge> playerBadges = new LinkedList<>();

    for (UUID badgeUUID : addon.badges.keySet()) {
      Badge badge = addon.badges.get(badgeUUID);

      if (badge.players().contains(uuid))
        playerBadges.add(badge);
    }

    return playerBadges;
  }
}
