package net.crazy.badges.core.ui.snapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.crazy.badges.api.Badge;
import net.crazy.badges.core.BadgesAddon;
import net.labymod.api.Laby;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.laby3d.renderer.snapshot.AbstractLabySnapshot;
import net.labymod.api.laby3d.renderer.snapshot.Extras;

public class BadgePlayerSnapshot extends AbstractLabySnapshot {

  private final UUID uuid;
  private final List<Badge> badges;
  private final boolean enabled;
  private final boolean compactBadges;
  private final boolean showOnBadges;

  public BadgePlayerSnapshot(Player player, Extras extras, BadgesAddon addon) {
    super(extras);
    this.uuid = player.getUniqueId();
    this.badges = BadgesAddon.badgeManager().getPlayerBadges(player);
    this.enabled = addon.configuration().enabled().get();
    this.compactBadges = addon.configuration().compactBadges().get();
    this.showOnBadges = addon.configuration().showOwn().get();
  }

  public boolean isEnabled() {
    return this.enabled;
  }

  public boolean hasBadges() {
    return !this.badges.isEmpty();
  }

  public boolean passesSelfCheck() {
    return this.showOnBadges || !this.uuid.equals(Laby.labyAPI().getUniqueId());
  }

  public List<Badge> getBadges() {
    if(!this.compactBadges) return this.badges;
    List<Badge> badges = new ArrayList<>(this.badges);
    badges.removeIf(badge ->
        badge.getId() == Badge.ONE_YEAR_STREAK && this.hasBadge(badges, Badge.TWO_YEAR_STREAK)
    );
    badges.removeIf(badge ->
        badge.getId() == Badge.TWO_YEAR_STREAK && this.hasBadge(badges, Badge.THREE_YEAR_STREAK)
    );
    badges.removeIf(badge ->
        badge.getId() == Badge.THREE_YEAR_STREAK && this.hasBadge(badges, Badge.HIGHEST_YEAR_STREAK)
    );
    return badges;
  }

  private boolean hasBadge(List<Badge> badges, int id) {
    for (Badge badge : badges)
      if (badge.getId() == id)
        return true;

    return false;
  }
}
