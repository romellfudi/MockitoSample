package fudi.freddy.mokitosample.share

import fudi.freddy.mokitosample.share.ShareContract.ShareUserActions
import fudi.freddy.mokitosample.share.ShareContract.ShareView

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 7/25/20
 */
class SharePresent(private var shareView: ShareView) : ShareUserActions {

    private var repository: Repository = RepositoryShare.instance

    internal fun visibleForTesting(repository: Repository) {
        this.repository = repository
    }

    override fun saveInput(string: String?) {
        repository.save(string)
        shareView.clearText()
        shareView.reLoadList()
    }

    override fun loadInput() {
        val result = repository.load()
        shareView.loadText(result)
    }

    override fun display(dat: String?) {
        shareView.loadText(dat)
    }

}