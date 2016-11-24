package crazypants.enderio.conduit.me;

import appeng.api.AEApi;
import crazypants.enderio.EnderIO;
import crazypants.enderio.ModObject;
import crazypants.enderio.conduit.AbstractItemConduit;
import crazypants.enderio.conduit.ConduitDisplayMode;
import crazypants.enderio.conduit.IConduit;
import crazypants.enderio.conduit.ItemConduitSubtype;
import crazypants.enderio.gui.IconEIO;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemMEConduit extends AbstractItemConduit {

  private static ItemConduitSubtype[] subtypes = new ItemConduitSubtype[] {
      new ItemConduitSubtype(ModObject.itemMEConduit.name(), EnderIO.DOMAIN + ":itemMeConduit"),
      new ItemConduitSubtype(ModObject.itemMEConduit.name() + "Dense", EnderIO.DOMAIN + ":itemMeConduitDense")
  };

  public static ItemMEConduit create() {
    if (MEUtil.isMEEnabled()) {
      ItemMEConduit result = new ItemMEConduit();
      result.init();
      ConduitDisplayMode.registerDisplayMode(new ConduitDisplayMode(IMEConduit.class, IconEIO.WRENCH_OVERLAY_ME, IconEIO.WRENCH_OVERLAY_ME_OFF));
      return result;
    }
    return null;
  }

  protected ItemMEConduit() {
    super(ModObject.itemMEConduit, subtypes);
  }

  @Override
  public Class<? extends IConduit> getBaseConduitType() {
    return IMEConduit.class;
  }

  @Override
  public IConduit createConduit(ItemStack item, EntityPlayer player) {
    MEConduit con = new MEConduit(item.getItemDamage());
    con.setPlayerID(AEApi.instance().registries().players().getID(player));
    return con;
  }
  
  @Override
  public boolean shouldHideFacades(ItemStack stack, EntityPlayer player) {
    return true;
  }
}