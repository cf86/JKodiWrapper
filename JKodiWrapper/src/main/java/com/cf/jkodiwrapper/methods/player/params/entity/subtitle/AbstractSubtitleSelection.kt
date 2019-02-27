package com.cf.jkodiwrapper.methods.player.params.entity.subtitle

import com.cf.jkodiwrapper.types.subtitle.SubtitleOperation

abstract class AbstractSubtitleSelection {

    abstract fun getValue(): String

    companion object {
        @JvmStatic
        fun getIndexSubtitle(index: Int) = SubtitleIndexSelection(index)

        @JvmStatic
        fun getSubtitleOperation(op: SubtitleOperation) = SubtitleOperationSelection(op)
    }
}