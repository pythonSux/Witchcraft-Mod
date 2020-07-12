package com.xX_deadbush_Xx.witchcraftmod.common.rituals.small;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.xX_deadbush_Xx.witchcraftmod.api.ritual.AbstractSmallRitual;
import com.xX_deadbush_Xx.witchcraftmod.api.ritual.IContinuousRitual;
import com.xX_deadbush_Xx.witchcraftmod.api.ritual.IRitualConfig;
import com.xX_deadbush_Xx.witchcraftmod.api.ritual.SmallRitualConfig;
import com.xX_deadbush_Xx.witchcraftmod.api.util.helpers.RitualHelper;
import com.xX_deadbush_Xx.witchcraftmod.client.effect.ClientParticleHandler.EffectType;
import com.xX_deadbush_Xx.witchcraftmod.common.blocks.blockstate.GlowType;
import com.xX_deadbush_Xx.witchcraftmod.common.network.WitchcraftPacketHandler;
import com.xX_deadbush_Xx.witchcraftmod.common.network.packets.client.WitchcraftParticlePacket;
import com.xX_deadbush_Xx.witchcraftmod.common.tile.AbstractRitualCore;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SmallGrowthRitual extends AbstractSmallRitual implements IContinuousRitual {
	public static final SmallRitualConfig config = new SmallRitualConfig(Blocks.EMERALD_BLOCK, Blocks.EMERALD_BLOCK, Blocks.EMERALD_BLOCK, Blocks.EMERALD_BLOCK);
	private List<BlockPos> blockstocheck = new ArrayList<>();
	private int manaconsumecooldown = 20;
	private static Random rand =  new Random();
	
	public SmallGrowthRitual(AbstractRitualCore tile, PlayerEntity player) {
		super(tile, player);
		
		//init blockstocheck
		for(Direction direction : RitualHelper.getHorizontalDirections()) {
			BlockPos pos1 = tile.getPos().offset(direction, 3);
			BlockPos pos2 = pos1.offset(direction);
			for(int i = -4; i < 3; i++) {
				blockstocheck.add(pos1.offset(direction.rotateY(), i));
				blockstocheck.add(pos2.offset(direction.rotateY(), i));
			}
		}
	}
	
	public static SmallGrowthRitual create(AbstractRitualCore tile, PlayerEntity player) {
		return new SmallGrowthRitual(tile, player);
	}

	@Override
	public void activate() {
		if(!conditionsMet()) { 
			stopRitual(false);
		}
	}

	@Override
	public GlowType getGlowType() {
		return GlowType.GREEN;
	}

	@Override
	public int manaPerSecond() {
		return 10;
	}

	@Override
	public void effectTick() {
		BlockPos pos = this.tile.getPos();
		for(BlockPos postocheck : this.blockstocheck) {
			accelerateGrowth(postocheck);
		}
		
		WitchcraftPacketHandler.sendToNearby(this.worldIn, pos, new WitchcraftParticlePacket(EffectType.GROWTH_RITUAL, pos.getX(), pos.getY(), pos.getZ(), 1));
	}
	
	private void accelerateGrowth(BlockPos pos) {
		BlockState state = this.worldIn.getBlockState(pos);
		Block block = state.getBlock();
		if(block instanceof CropsBlock) {
			if(((CropsBlock)block).isMaxAge(state)) return;
			if(this.rand.nextInt(80)==0) block.tick(state, (ServerWorld) this.worldIn, pos, this.rand);
		}
	}

	@Override
	public IRitualConfig getConfig() {
		return SmallGrowthRitual.config;
	}
}