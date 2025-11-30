package net.crazy.badges.core.ui.snapshot;

import net.crazy.badges.core.BadgesAddon;
import net.labymod.api.client.entity.player.Player;
import net.labymod.api.laby3d.renderer.snapshot.Extras;
import net.labymod.api.laby3d.renderer.snapshot.LabySnapshotFactory;
import net.labymod.api.service.annotation.AutoService;

@AutoService(LabySnapshotFactory.class)
public class BadgePlayerSnapshotFactory extends LabySnapshotFactory<Player, BadgePlayerSnapshot> {

  private final BadgesAddon addon;

  public BadgePlayerSnapshotFactory(BadgesAddon addon) {
    super(BadgesExtraKeys.BADGE_PLAYER);
    this.addon = addon;
  }

  @Override
  public BadgePlayerSnapshot create(Player player, Extras extras) {
    return new BadgePlayerSnapshot(player, extras, this.addon);
  }
}
