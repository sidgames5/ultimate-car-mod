package de.maxhenkel.car.gui;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.matrix.MatrixStack;
import de.maxhenkel.car.Main;
import de.maxhenkel.car.blocks.tileentity.TileEntitySplitTank;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class GuiSplitTank extends GuiBase<ContainerSplitTank> {

    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Main.MODID, "textures/gui/gui_split_tank.png");

    private PlayerInventory playerInv;
    private TileEntitySplitTank tile;

    public GuiSplitTank(ContainerSplitTank containerSplitTank, PlayerInventory playerInv, ITextComponent title) {
        super(GUI_TEXTURE, containerSplitTank, playerInv, title);
        this.playerInv = playerInv;
        this.tile = containerSplitTank.getSplitTank();

        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void func_230451_b_(MatrixStack matrixStack, int mouseX, int mouseY) {
        super.func_230451_b_(matrixStack, mouseX, mouseY);

        // Title
        field_230712_o_.func_238422_b_(matrixStack, playerInv.getDisplayName(), 8, this.ySize - 96 + 2, FONT_COLOR);

        if (mouseX >= guiLeft + 50 && mouseX <= guiLeft + 16 + 50) {
            if (mouseY >= guiTop + 8 && mouseY <= guiTop + 57 + 8) {
                List<IFormattableTextComponent> list = new ArrayList<>();
                list.add(new TranslationTextComponent("tooltip.mix", tile.getCurrentMix()));
                func_238654_b_(matrixStack, list, mouseX - guiLeft, mouseY - guiTop);
            }
        }

        if (mouseX >= guiLeft + 120 && mouseX <= guiLeft + 16 + 120) {
            if (mouseY >= guiTop + 8 && mouseY <= guiTop + 57 + 8) {
                List<IFormattableTextComponent> list = new ArrayList<>();
                list.add(new TranslationTextComponent("tooltip.glycerin", tile.getCurrentGlycerin()));
                func_238654_b_(matrixStack, list, mouseX - guiLeft, mouseY - guiTop);
            }
        }

        if (mouseX >= guiLeft + 141 && mouseX <= guiLeft + 16 + 141) {
            if (mouseY >= guiTop + 8 && mouseY <= guiTop + 57 + 8) {
                List<IFormattableTextComponent> list = new ArrayList<>();
                list.add(new TranslationTextComponent("tooltip.bio_diesel", tile.getCurrentBioDiesel()));
                func_238654_b_(matrixStack, list, mouseX - guiLeft, mouseY - guiTop);
            }
        }

        if (mouseX >= guiLeft + 79 && mouseX <= guiLeft + 24 + 79) {
            if (mouseY >= guiTop + 34 && mouseY <= guiTop + 17 + 34) {
                List<IFormattableTextComponent> list = new ArrayList<>();
                list.add(new TranslationTextComponent("tooltip.progress", ((int) (getProgress() * 100F))));
                func_238654_b_(matrixStack, list, mouseX - guiLeft, mouseY - guiTop);
            }
        }
    }

    @Override
    protected void func_230450_a_(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        super.func_230450_a_(matrixStack, partialTicks, mouseX, mouseY);
        drawProgress(matrixStack);
        drawMix(matrixStack);
        drawBioDiesel(matrixStack);
        drawGlycerin(matrixStack);
    }

    public void drawGlycerin(MatrixStack matrixStack) {
        float perc = getGlycerin();

        int texX = 192;
        int texY = 17;
        int texW = 16;
        int texH = 57;
        int targetX = 120;
        int targetY = 8;

        int scHeight = (int) (texH * (1 - perc));
        int i = this.guiLeft;
        int j = this.guiTop;
        func_238474_b_(matrixStack, i + targetX, j + targetY + scHeight, texX, texY + scHeight, texW, texH - scHeight);
    }

    public void drawBioDiesel(MatrixStack matrixStack) {
        float perc = getBioDiesel();

        int texX = 208;
        int texY = 17;
        int texW = 16;
        int texH = 57;
        int targetX = 141;
        int targetY = 8;

        int scHeight = (int) (texH * (1 - perc));
        int i = this.guiLeft;
        int j = this.guiTop;
        func_238474_b_(matrixStack, i + targetX, j + targetY + scHeight, texX, texY + scHeight, texW, texH - scHeight);
    }

    public void drawMix(MatrixStack matrixStack) {
        float perc = getMix();

        int texX = 176;
        int texY = 17;
        int texW = 16;
        int texH = 57;
        int targetX = 50;
        int targetY = 8;

        int scHeight = (int) (texH * (1 - perc));
        int i = this.guiLeft;
        int j = this.guiTop;
        func_238474_b_(matrixStack, i + targetX, j + targetY + scHeight, texX, texY + scHeight, texW, texH - scHeight);
    }

    public void drawProgress(MatrixStack matrixStack) {
        float perc = getProgress();

        int texX = 176;
        int texY = 0;
        int texW = 24;
        int texH = 17;
        int targetX = 79;
        int targetY = 34;

        int scWidth = (int) (texW * perc);
        int i = this.guiLeft;
        int j = this.guiTop;
        func_238474_b_(matrixStack, i + targetX, j + targetY, texX, texY, scWidth, texH);
    }

    public float getMix() {
        return ((float) tile.getCurrentMix()) / ((float) tile.maxMix);
    }

    public float getBioDiesel() {
        return ((float) tile.getCurrentBioDiesel()) / ((float) tile.maxBioDiesel);
    }

    public float getGlycerin() {
        return ((float) tile.getCurrentGlycerin()) / ((float) tile.maxGlycerin);
    }

    public float getProgress() {
        if (tile.getTimeToGenerate() == 0) {
            return 0;
        }

        int time = tile.generatingTime - tile.getTimeToGenerate();
        return ((float) time) / ((float) tile.generatingTime);
    }

}
