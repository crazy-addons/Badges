package net.crazy.badges.api;

import net.labymod.api.client.entity.player.Player;
import net.labymod.api.reference.annotation.Referenceable;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.UUID;

@Referenceable
public interface BadgeManager {

  @NotNull
  default List<Badge> getPlayerBadges(Player player) {
    return this.getPlayerBadges(player.getUniqueId());
  }

  @NotNull
  List<Badge> getPlayerBadges(UUID uuid);

  void removeFromPlayerCache(UUID uuid);

  void clearPlayerCache();

  @NotNull
  List<Badge> getBadges();

  void cacheBadges();

}
