package com.wuyanbo.panel;

import com.wuyanbo.dao.RankDao;
import com.wuyanbo.po.Rank;
import com.wuyanbo.util.ImageUtil;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

/**
 * @description:
 * @author: wuyanbo
 * @create: 2019-12-06 14:02
 */

public class RankPanel extends JPanel {


    private Image bg_rank;
    private Font font;
    private JTable table;
    //排行榜数据
    private List<Rank> ranks;


    public RankPanel() {

        initPanel();
        initComponents();
        initTable();
        addComponents();
    }

    private void initTable() {
        //初始化数据
        ranks = new RankDao().selectRanks();
        int size = ranks.size();
        Object[][] rowData = new Object[size][4];
        Object[] columNames = {"排名", "姓名", "分数", "时间"};
        Rank rank = null;
        for (int i = 0; i < size; i++) {
            rank = ranks.get(i);
            rowData[i][0] = i + 1;
            rowData[i][1] = rank.getUser().getUserName();
            rowData[i][2] = rank.getScore();
            rowData[i][3] = rank.formatDate();
        }

        //init
        table = new JTable(rowData, columNames) {
            //单元格透明
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                if (component instanceof JComponent) {
                    ((JComponent) component).setOpaque(false);
                }
                return component;
            }
        };
        //无网格
        table.setShowGrid(false);
        //
        table.setBounds(100, 180, 500, 300);
        //设置无数据不可见
        table.setOpaque(false);
        //设置字体
        table.setFont(font);
        //设置行高
        table.setRowHeight(40);
        //不可编辑
        table.setEnabled(false);
    }

    //添加组件
    private void addComponents() {
        this.add(table);
    }

    //初始化组件
    private void initComponents() {
        this.bg_rank = ImageUtil.getImage("/img/bg_rank.png");
        this.font = new Font("楷体", Font.BOLD, 18);
    }

    /**
     * 初始化面板信息
     */
    private void initPanel() {
        //设置面板位置
        this.setBounds(0, 0, 700, 500);
        //设置布局
        this.setLayout(null);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg_rank, 0, 0, this);
    }

}
