package tschipp.tschipplib.network;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class STileEntitySyncPacketHandler implements IMessageHandler<STileEntitySyncPacket, IMessage> {


	@Override
	public IMessage onMessage(STileEntitySyncPacket message, MessageContext ctx) {
		IThreadListener mainThread = (WorldServer)ctx.getServerHandler().player.world;
		mainThread.addScheduledTask(new Runnable() {

			@Override
			public void run() {

				NBTTagCompound data = message.data;
				BlockPos pos = new BlockPos(data.getInteger("x"), data.getInteger("y"), data.getInteger("z"));
				NBTTagCompound tileData = data.getCompoundTag("data");
				
				TileEntity tile = ctx.getServerHandler().player.world.getTileEntity(pos);

				if(tile != null)
				{
					if(doTileCheck(tile))
						tile.readFromNBT(tileData);
				}
				

			}});

		return null;
	}
	
	private boolean doTileCheck(TileEntity t)
	{
		String clazz = t.getClass().getName();
		switch(clazz)
		{
		case "tschipp.extraambiance.tileentity.TileEntityColoredLight":
		case "tschipp.extraambiance.tileentity.TileEntityParticleEmitter":
		case "tschipp.extraambiance.tileentity.TileEntitySoundEmitter":
			return true;
		default:
			return false;
		}
	}

}
