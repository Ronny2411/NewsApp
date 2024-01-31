package com.example.newsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.newsapp.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String{
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(string: String): Source{
        return string.split(",").let {strArray->
            Source(id = strArray[0], name = strArray[1])
        }
    }
}