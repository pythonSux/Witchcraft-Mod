package com.xX_deadbush_Xx.hocus.data;

import com.xX_deadbush_Xx.hocus.Hocus;
import com.xX_deadbush_Xx.hocus.common.blocks.blockstate.ModBlockStateProperties;
import com.xX_deadbush_Xx.hocus.common.register.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class BlockStatesDataGen extends BlockStateProvider {

	public BlockStatesDataGen(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
		super(generator, modId, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		//simple blocks
		simpleBlock(ModBlocks.ADONIS.get(), models().getExistingFile(getModelPath(ModBlocks.ADONIS)));
		simpleBlock(ModBlocks.BELLADONNA.get(), models().getExistingFile(getModelPath(ModBlocks.BELLADONNA)));
		simpleBlock(ModBlocks.DREADWOOD_LEAVES.get(), models().getExistingFile(getModelPath(ModBlocks.DREADWOOD_LEAVES)));
		simpleBlock(ModBlocks.DREADWOOD_PLANKS.get(), models().getExistingFile(getModelPath(ModBlocks.DREADWOOD_PLANKS)));
		simpleBlock(ModBlocks.DREADWOOD_SAPLING.get(), models().getExistingFile(getModelPath(ModBlocks.DREADWOOD_SAPLING)));
		simpleBlock(ModBlocks.HARDENED_NETHERRACK.get(), models().getExistingFile(getModelPath(ModBlocks.HARDENED_NETHERRACK)));
		simpleBlock(ModBlocks.HELLSHROOM.get(), models().getExistingFile(getModelPath(ModBlocks.HELLSHROOM)));
		simpleBlock(ModBlocks.RITUAL_PEDESTAL.get(), models().getExistingFile(getModelPath(ModBlocks.RITUAL_PEDESTAL)));
		simpleBlock(ModBlocks.RITUAL_STONE.get(), models().getExistingFile(getModelPath(ModBlocks.RITUAL_STONE)));
		simpleBlock(ModBlocks.VIBRANT_BLOCK.get(), models().getExistingFile(getModelPath(ModBlocks.VIBRANT_BLOCK)));
		simpleBlock(ModBlocks.VIBRANT_CRYSTAL_ORE.get(), models().getExistingFile(getModelPath(ModBlocks.VIBRANT_CRYSTAL_ORE)));
		simpleBlock(ModBlocks.CRYSTAL_RECHARGER.get(), models().getExistingFile(getModelPath(ModBlocks.CRYSTAL_RECHARGER)));
		simpleBlock(ModBlocks.ENERGY_RELAY.get(), models().getExistingFile(getModelPath(ModBlocks.ENERGY_RELAY)));
		simpleBlock(ModBlocks.CREATIVE_MANA_SOURCE.get(), models().getExistingFile(getModelPath(ModBlocks.CREATIVE_MANA_SOURCE)));
		simpleBlock(ModBlocks.ONYX_ORE.get(), models().getExistingFile(getModelPath(ModBlocks.ONYX_ORE)));
		simpleBlock(ModBlocks.CAVE_FLOWER.get(), models().getExistingFile(getModelPath(ModBlocks.CAVE_FLOWER)));
		simpleBlock(ModBlocks.SHALE.get(), models().getExistingFile(getModelPath(ModBlocks.SHALE)));
		simpleBlock(ModBlocks.SHALE_BRICKS.get(), models().getExistingFile(getModelPath(ModBlocks.SHALE_BRICKS)));
		simpleBlock(ModBlocks.CHISELED_SHALE.get(), models().getExistingFile(getModelPath(ModBlocks.CHISELED_SHALE)));
		simpleBlock(ModBlocks.POLISHED_WOOD.get(), models().getExistingFile(getModelPath(ModBlocks.POLISHED_WOOD)));

		tallBlock(ModBlocks.SWIRLY_PLANT);
		
		horizontalBlock(ModBlocks.TABLE.get(), models().getExistingFile(getModelPath(ModBlocks.TABLE)));
		horizontalBlock(ModBlocks.TOOL_TABLE.get(), models().getExistingFile(getModelPath(ModBlocks.TOOL_TABLE)));
		horizontalBlock(ModBlocks.AUTO_TOOLTABLE.get(), models().getExistingFile(getModelPath(ModBlocks.AUTO_TOOLTABLE)));
		horizontalBlock(ModBlocks.DRYING_RACK.get(), models().getExistingFile(getModelPath(ModBlocks.DRYING_RACK)));
		horizontalBlock(ModBlocks.RED_TOTEM.get(), models().getExistingFile(getModelPath(ModBlocks.RED_TOTEM)));
		horizontalBlock(ModBlocks.GREEN_TOTEM.get(), models().getExistingFile(getModelPath(ModBlocks.GREEN_TOTEM)));
		horizontalBlock(ModBlocks.PURPLE_TOTEM.get(), models().getExistingFile(getModelPath(ModBlocks.PURPLE_TOTEM)));

		
		getVariantBuilder(ModBlocks.CANDLE.get())
			.partialState().with(ModBlockStateProperties.CANDLES_1_3, 1).setModels(newConfiguredModel("one_" + getBlockName(ModBlocks.CANDLE)))
			.partialState().with(ModBlockStateProperties.CANDLES_1_3, 2).setModels(newConfiguredModel("two_" + getBlockName(ModBlocks.CANDLE) + "s"))
			.partialState().with(ModBlockStateProperties.CANDLES_1_3, 3).setModels(newConfiguredModel("three_" + getBlockName(ModBlocks.CANDLE) + "s"));
		
		getVariantBuilder(ModBlocks.STONE_MORTAR.get())
			.partialState().with(ModBlockStateProperties.OIL_FILLLEVEL, 0).setModels(newConfiguredModel(getBlockName(ModBlocks.STONE_MORTAR)))
			.partialState().with(ModBlockStateProperties.OIL_FILLLEVEL, 1).setModels(newConfiguredModel("level_1_" + getBlockName(ModBlocks.STONE_MORTAR)))
			.partialState().with(ModBlockStateProperties.OIL_FILLLEVEL, 2).setModels(newConfiguredModel("level_2_" + getBlockName(ModBlocks.STONE_MORTAR)))
			.partialState().with(ModBlockStateProperties.OIL_FILLLEVEL, 3).setModels(newConfiguredModel("level_3_" + getBlockName(ModBlocks.STONE_MORTAR)));
		
		getVariantBuilder(ModBlocks.FIRE_BOWL.get())
			.partialState().with(BlockStateProperties.LIT, true).setModels(newConfiguredModel(getBlockName(ModBlocks.FIRE_BOWL) + "_lit"))
			.partialState().with(BlockStateProperties.LIT, false).setModels(newConfiguredModel(getBlockName(ModBlocks.FIRE_BOWL) + "_unlit"));

		buildMushroomBlock(ModBlocks.HELLSHROOM_BLOCK);
		buildMushroomBlock(ModBlocks.HELLSHROOM_STEM);
		
		poisonIvy(ModBlocks.POISON_IVY);
	}
	
	private void poisonIvy(RegistryObject<Block> block) {
		getMultipartBuilder(block.get())
			.part().modelFile(models().getExistingFile(getModelPath(getBlockName(block) + "_north"))).uvLock(true).addModel().condition(BlockStateProperties.NORTH, true).end()
			.part().modelFile(models().getExistingFile(getModelPath(getBlockName(block) + "_south"))).uvLock(true).addModel().condition(BlockStateProperties.SOUTH, true).end()
			.part().modelFile(models().getExistingFile(getModelPath(getBlockName(block) + "_east"))).uvLock(true).addModel().condition(BlockStateProperties.EAST, true).end()
			.part().modelFile(models().getExistingFile(getModelPath(getBlockName(block) + "_west"))).uvLock(true).addModel().condition(BlockStateProperties.WEST, true).end()
			.part().modelFile(models().getExistingFile(getModelPath(getBlockName(block) + "_up"))).uvLock(true).addModel().condition(BlockStateProperties.UP, true).end()
			.part().modelFile(models().getExistingFile(getModelPath(getBlockName(block) + "_down"))).uvLock(true).addModel().condition(BlockStateProperties.DOWN, true).end();
	}

	private void tallBlock(RegistryObject<Block> block) {
        getVariantBuilder(block.get())
        .partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)
            .modelForState().modelFile(models().getExistingFile(getModelPath(getBlockName(block) + "_top"))).addModel()
        .partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)
        	.modelForState().modelFile(models().getExistingFile(getModelPath(getBlockName(block) + "_bottom"))).addModel();
	}
	
	private ConfiguredModel newConfiguredModel(String path) {
		return new ConfiguredModel(models().getExistingFile(getModelPath(path)));
	}
	
	private void buildMushroomBlock(RegistryObject<Block> block) {
		getMultipartBuilder(block.get())
			.part().modelFile(models().getExistingFile(getModelPath(block))).addModel().condition(BlockStateProperties.NORTH, true).end()
			.part().modelFile(models().getExistingFile(getModelPath(getBlockName(block) + "_inside"))).addModel().condition(BlockStateProperties.NORTH, false).end()
			.part().modelFile(models().getExistingFile(getModelPath(block))).uvLock(true).rotationY(180).addModel().condition(BlockStateProperties.SOUTH, true).end()
			.part().modelFile(models().getExistingFile(getModelPath(getBlockName(block) + "_inside"))).uvLock(false).rotationY(180).addModel().condition(BlockStateProperties.SOUTH, false).end()
			.part().modelFile(models().getExistingFile(getModelPath(block))).uvLock(true).rotationY(90).addModel().condition(BlockStateProperties.EAST, true).end()
			.part().modelFile(models().getExistingFile(getModelPath(getBlockName(block) + "_inside"))).uvLock(false).rotationY(90).addModel().condition(BlockStateProperties.EAST, false).end()
			.part().modelFile(models().getExistingFile(getModelPath(block))).uvLock(true).rotationY(270).addModel().condition(BlockStateProperties.WEST, true).end()
			.part().modelFile(models().getExistingFile(getModelPath(getBlockName(block) + "_inside"))).uvLock(false).rotationY(270).addModel().condition(BlockStateProperties.WEST, false).end()
			.part().modelFile(models().getExistingFile(getModelPath(block))).uvLock(true).rotationX(270).addModel().condition(BlockStateProperties.UP, true).end()
			.part().modelFile(models().getExistingFile(getModelPath(getBlockName(block) + "_inside"))).uvLock(false).rotationX(270).addModel().condition(BlockStateProperties.UP, false).end()
			.part().modelFile(models().getExistingFile(getModelPath(block))).uvLock(true).rotationX(90).addModel().condition(BlockStateProperties.DOWN, true).end()
			.part().modelFile(models().getExistingFile(getModelPath(getBlockName(block) + "_inside"))).uvLock(false).rotationX(90).addModel().condition(BlockStateProperties.DOWN, false).end();
	}
	
	private String getBlockName(RegistryObject<Block> block) {
		return block.get().getRegistryName().getPath();
	}
	
	private ResourceLocation getModelPath(String name) {
		return new ResourceLocation(Hocus.MOD_ID, "block/" + name);
	}
	
	private ResourceLocation getModelPath(RegistryObject<Block> block) {
		return getModelPath(getBlockName(block));
	}
	
	@Override
	public String getName() {
		return "Hocus Blockstates";
	}
}
