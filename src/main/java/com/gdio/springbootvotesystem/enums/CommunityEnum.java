package com.gdio.springbootvotesystem.enums;

/**
 * @author gdio
 * @create 2020-02-28 22:52
 */
public enum CommunityEnum {
    MAINCOMMUNITY(1,"主干道","寂寞空虚冷的主社区"),
    SPORTCOMMUNITY(2,"运动装备","湖人总冠军!(破音)"),
    MOVIECOMMUNITY(3,"影视交流","我永远喜欢小时代"),
    FUNNYCOMMUITY(4,"搞笑趣味","从前有个人叫小明，小明没听见..."),
    GAMECOMMUNITY(5,"游戏交流","这次真的是最后一把");
    private final int type;
    private final String name;
    private final String discribe;
    private CommunityEnum(int type,String name,String discribe){
        this.type=type;
        this.name=name;
        this.discribe=discribe;
    }
}
