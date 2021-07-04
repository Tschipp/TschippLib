package tschipp.tschipplib.helper;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import tschipp.tschipplib.TschippLib;
import tschipp.tschipplib.network.CTileEntitySyncPacket;
import tschipp.tschipplib.network.STileEntitySyncPacket;

public class NetworkHelper
{

	public static void sendServerTileUpdate(TileEntity tile)
	{
		if(tile == null) return;
        if(tile.getWorld() != null && tile.getWorld().isRemote)
        {
            NBTTagCompound compound = new NBTTagCompound();
            compound = tile.writeToNBT(compound);

            NBTTagCompound data = new NBTTagCompound();
            data.setTag("data", compound);
            data.setInteger("x", tile.getPos().getX());
            data.setInteger("y", tile.getPos().getY());
            data.setInteger("z", tile.getPos().getZ());
            TschippLib.network.sendToServer(new STileEntitySyncPacket(data));
        }
    
	}
	
	public static void sendClientTileUpdate(TileEntity tile)
	{
		if(tile == null) return;
        if(tile.getWorld() != null && !tile.getWorld().isRemote)
        {
            NBTTagCompound compound = new NBTTagCompound();
            compound = tile.writeToNBT(compound);

            NBTTagCompound data = new NBTTagCompound();
            data.setTag("data", compound);
            data.setInteger("x", tile.getPos().getX());
            data.setInteger("y", tile.getPos().getY());
            data.setInteger("z", tile.getPos().getZ());
            TschippLib.network.sendToAllAround(new CTileEntitySyncPacket(data), new NetworkRegistry.TargetPoint(tile.getWorld().provider.getDimension(), tile.getPos().getX(), tile.getPos().getY(), tile.getPos().getZ(), 64));
        }
    
	}
	
}
