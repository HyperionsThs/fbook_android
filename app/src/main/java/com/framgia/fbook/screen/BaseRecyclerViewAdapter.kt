package com.framgia.fbook.screen

import android.content.Context
import android.support.v7.widget.RecyclerView

/**
 * Created by le.quang.dao on 14/03/2017.
 * Base Adapter.

 * @param <V> is a type extend from [RecyclerView.ViewHolder]
</V> */

abstract class BaseRecyclerViewAdapter<V : RecyclerView.ViewHolder> protected constructor(
    protected val context: Context) : RecyclerView.Adapter<V>()
