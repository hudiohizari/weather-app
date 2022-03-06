package id.hudiohizari.weather.data.adapter.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import id.hudiohizari.weather.databinding.ListItemReloadDefaultBinding

class DefaultReloadListItem(
    private val listener: Listener
) : AbstractBindingItem<ListItemReloadDefaultBinding>(),
    DiffableListItemType {

    override fun itemIdentifier(): Any = this.hashCode()

    override fun comparableContents(): List<Any> = listOf()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ListItemReloadDefaultBinding {
        return ListItemReloadDefaultBinding.inflate(inflater, parent, false)
    }

    override val type: Int get() = hashCode()

    override fun bindView(binding: ListItemReloadDefaultBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.onClickReload = View.OnClickListener { listener.reload() }
    }

    interface Listener {
        fun reload()
    }
}