package com.pjt.erpit.biz.entity.history.convert;

import com.pjt.erpit.biz.entity.Item;
import com.pjt.erpit.biz.entity.history.ItemHistory;
import org.mapstruct.Mapper;

/**
 * Item History Convert
 */
@Mapper(componentModel = "spring")
public interface ItemConvert {
    ItemHistory toItemHistory(Item item);
}
