package net.crazy.badges.core.ui.activity;

import java.util.ArrayList;
import java.util.List;
import net.crazy.badges.api.Badge;
import net.crazy.badges.core.BadgesAddon;
import net.crazy.badges.core.ui.widget.BadgeWidget;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.Link;
import net.labymod.api.client.gui.screen.widget.action.ListSession;
import net.labymod.api.client.gui.screen.widget.widgets.layout.ScrollWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.TilesGridWidget;

@AutoActivity
@Link("overview.lss")
public class BadgeActivity extends Activity {

  private final List<BadgeWidget> badgeWidgets = new ArrayList<>();
  private final TilesGridWidget<BadgeWidget> gridWidget;
  private final ListSession<?> listSession = new ListSession<>();

  public BadgeActivity() {
    for (Badge badge : BadgesAddon.badgeManager().getBadges())
      this.badgeWidgets.add(new BadgeWidget(badge.getIcon(), badge.getName(), badge.getDescription()));

    this.gridWidget = new TilesGridWidget<>();
    this.gridWidget.addId("gridWidget");
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);

    for (BadgeWidget badgeWidget : this.badgeWidgets)
      this.gridWidget.addTile(badgeWidget);


    ScrollWidget scrollWidget = new ScrollWidget(this.gridWidget, this.listSession);
    this.document().addChild(scrollWidget);
  }
}
