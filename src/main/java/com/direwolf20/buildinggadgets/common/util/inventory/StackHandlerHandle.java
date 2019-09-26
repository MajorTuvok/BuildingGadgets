package com.direwolf20.buildinggadgets.common.util.inventory;

import com.direwolf20.buildinggadgets.api.materials.UniqueItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public final class StackHandlerHandle implements IStackHandle {
    private final IItemHandler handler;
    private final int slot;

    public StackHandlerHandle(IItemHandler handler, int slot) {
        this.handler = handler;
        this.slot = slot;
    }

    @Override
    public boolean isReady() {
        return ! getStack().isEmpty();
    }

    @Override
    public int match(UniqueItem item, int count, boolean simulate) {
        ItemStack stack = getStack();
        if (item.matches(stack)) {
            ItemStack resultStack = handler.extractItem(slot, count, simulate);
            return resultStack.getCount();
        }
        return 0;
    }

    @Override
    public Item getItem() {
        return getStack().getItem();
    }

    private ItemStack getStack() {
        return handler.getStackInSlot(slot);
    }
}
