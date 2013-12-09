package mantle.blocks;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import mantle.blocks.iface.IDebuggable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Root class for inheriting the Minecraft Block.
 *
 * @author Sunstrike <sun@sunstrike.io>
 */
public abstract class MantleBlock extends Block {

    public MantleBlock(int id, Material material) {
        super(id, material);
    }

    // IDebuggable support - Uses a stick for debug purposes.
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && player.getHeldItem().getItem() == Item.stick)
        {
            TileEntity te = world.getBlockTileEntity(x, y, z);
            if (te instanceof IDebuggable)
            {
                ((IDebuggable) te).sendDebugToPlayer(player);
            }
        }

        super.onBlockClicked(world, x, y, z, player);
    }
}
