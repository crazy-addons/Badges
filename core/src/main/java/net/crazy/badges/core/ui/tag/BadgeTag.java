package net.crazy.badges.core.ui.tag;

import java.util.ArrayList;
import java.util.List;
import net.crazy.badges.api.Badge;
import net.crazy.badges.core.AddonConfiguration;
import net.crazy.badges.core.BadgesAddon;
import net.crazy.badges.core.ui.snapshot.BadgePlayerSnapshot;
import net.crazy.badges.core.ui.snapshot.BadgesExtraKeys;
import net.labymod.api.client.entity.player.tag.renderer.AbstractTagRenderer;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.client.render.state.entity.EntitySnapshot;
import net.labymod.api.laby3d.render.queue.SubmissionCollector;
import net.labymod.api.laby3d.render.queue.submissions.IconSubmission.DisplayMode;

public class BadgeTag extends AbstractTagRenderer {

  private static final float ICON_SIZE = 4F;

  private final AddonConfiguration config;

  private List<Icon> icons;
  private float width;
  private float height;

  public BadgeTag(BadgesAddon addon) {
    this.config = addon.configuration();
  }

  @Override
  public void begin(EntitySnapshot snapshot) {
    super.begin(snapshot);
    this.icons = this.getIcons(snapshot);
    this.width = ICON_SIZE * this.icons.size();
    this.height = ICON_SIZE;
  }

  private List<Icon> getIcons(EntitySnapshot snapshot) {
    List<Icon> icons = new ArrayList<>();
    if(!snapshot.has(BadgesExtraKeys.BADGE_PLAYER) || snapshot.isDiscrete() || snapshot.isInvisible()) {
      return icons;
    }
    BadgePlayerSnapshot badgePlayer = snapshot.get(BadgesExtraKeys.BADGE_PLAYER);

    if(!badgePlayer.isEnabled() || !badgePlayer.passesSelfCheck() || !badgePlayer.hasBadges()) {
      return icons;
    }
    for(Badge badge : badgePlayer.getBadges()) {
      icons.add(badge.getIcon());
    }
    return icons;
  }

  @Override
  public void render(Stack stack, SubmissionCollector submissionCollector, EntitySnapshot snapshot) {
    float x = 0;
    for(Icon icon : this.icons) {
      submissionCollector.submitIcon(
          stack,
          icon,
          DisplayMode.NORMAL,
          x, 0,
          ICON_SIZE, ICON_SIZE,
          -1
      );
      x += ICON_SIZE;
    }
  }

  @Override
  public boolean isVisible() {
    return !this.icons.isEmpty();
  }

  @Override
  public float getWidth() {
    return this.width;
  }

  @Override
  public float getHeight() {
    return this.height;
  }

  @Override
  public float getScale() {
    return 2 + (this.config.size().get() - 1) * 5F / 9;
  }
}
