package net.crazy.badges.core;

import net.crazy.badges.api.BadgeManager;
import net.crazy.badges.api.generated.ReferenceStorage;
import net.crazy.badges.core.listener.PlayerListener;
import net.crazy.badges.core.ui.tag.BadgeTag;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.entity.player.tag.PositionType;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class BadgesAddon extends LabyAddon<AddonConfiguration> {

  private static BadgesAddon instance;

  @Override
  protected void enable() {
    instance = this;

    badgeManager().cacheBadges();

    this.registerSettingCategory();
    this.registerListener(new PlayerListener());
    this.labyAPI().tagRegistry().registerAfter(
        "friendtags_tag",
        "badge",
        PositionType.ABOVE_NAME,
        new BadgeTag(this)
    );

    this.logger().info("[Badges] Addon enabled.");
  }

  @Override
  protected Class<AddonConfiguration> configurationClass() {
    return AddonConfiguration.class;
  }

  public static BadgeManager badgeManager() {
    return ((ReferenceStorage) instance.referenceStorageAccessor()).badgeManager();
  }
}
