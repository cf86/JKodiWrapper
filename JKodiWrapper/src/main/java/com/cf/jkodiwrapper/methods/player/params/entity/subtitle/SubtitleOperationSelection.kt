package com.cf.jkodiwrapper.methods.player.params.entity.subtitle

import com.cf.jkodiwrapper.types.subtitle.SubtitleOperation

data class SubtitleOperationSelection(var op: SubtitleOperation) : AbstractSubtitleSelection() {

    override fun getValue(): String {
        return op.op
    }
}