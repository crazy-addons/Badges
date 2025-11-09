package net.crazy.badges.core.ui.snapshot;

import net.labymod.api.laby3d.renderer.snapshot.ExtraKey;

public class BadgesExtraKeys {

  public static final ExtraKey<BadgePlayerSnapshot> BADGE_PLAYER = ExtraKey.of(
      "badge_player",
      BadgePlayerSnapshot.class
  );

}
