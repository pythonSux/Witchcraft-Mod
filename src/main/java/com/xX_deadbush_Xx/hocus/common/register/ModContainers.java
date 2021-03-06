package com.xX_deadbush_Xx.hocus.common.register;

import com.xX_deadbush_Xx.hocus.Hocus;
import com.xX_deadbush_Xx.hocus.common.container.AutoToolTableContainer;
import com.xX_deadbush_Xx.hocus.common.container.BottomLessBagContainer;
import com.xX_deadbush_Xx.hocus.common.container.CrystalRechargerContainer;
import com.xX_deadbush_Xx.hocus.common.container.ToolTableContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, Hocus.MOD_ID);

	public static final RegistryObject<ContainerType<ToolTableContainer>> TOOL_TABLE = CONTAINER_TYPES
			.register("tool_table", () -> IForgeContainerType.create(ToolTableContainer::new));
	public static final RegistryObject<ContainerType<AutoToolTableContainer>> AUTO_TOOLTABLE = CONTAINER_TYPES
			.register("auto_tooltable", () -> IForgeContainerType.create(AutoToolTableContainer::new));
	public static final RegistryObject<ContainerType<CrystalRechargerContainer>> CRYSTAL_RECHARGER = CONTAINER_TYPES
			.register("crystal_recharger", () -> IForgeContainerType.create(CrystalRechargerContainer::new));

	public static final RegistryObject<ContainerType<BottomLessBagContainer>> BOTTOM_LESS_BAG = CONTAINER_TYPES.register("bottomless_bag",
			() -> IForgeContainerType.create(BottomLessBagContainer::new));
}
