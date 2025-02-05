package dev.dergoogler.mmrl.compat.impl

import com.topjohnwu.superuser.Shell
import dev.dergoogler.mmrl.compat.stub.IInstallCallback
import dev.dergoogler.mmrl.compat.stub.IModuleOpsCallback

internal class MagiskModuleManagerImpl(
    private val shell: Shell,
) : BaseModuleManagerImpl(shell) {
    override fun getManagerName(): String {
        return "Magisk"
    }


    override fun enable(id: String, useShell: Boolean, callback: IModuleOpsCallback) {
        val dir = modulesDir.resolve(id)
        if (!dir.exists()) callback.onFailure(id, null)

        runCatching {
            dir.resolve("remove").apply { if (exists()) delete() }
            dir.resolve("disable").apply { if (exists()) delete() }
        }.onSuccess {
            callback.onSuccess(id)
        }.onFailure {
            callback.onFailure(id, it.message)
        }
    }

    override fun disable(id: String, useShell: Boolean, callback: IModuleOpsCallback) {
        val dir = modulesDir.resolve(id)
        if (!dir.exists()) return callback.onFailure(id, null)

        runCatching {
            dir.resolve("remove").apply { if (exists()) delete() }
            dir.resolve("disable").createNewFile()
        }.onSuccess {
            callback.onSuccess(id)
        }.onFailure {
            callback.onFailure(id, it.message)
        }
    }

    override fun remove(id: String, useShell: Boolean, callback: IModuleOpsCallback) {
        val dir = modulesDir.resolve(id)
        if (!dir.exists()) return callback.onFailure(id, null)

        runCatching {
            dir.resolve("disable").apply { if (exists()) delete() }
            dir.resolve("remove").createNewFile()
        }.onSuccess {
            callback.onSuccess(id)
        }.onFailure {
            callback.onFailure(id, it.message)
        }
    }

    override fun install(path: String, callback: IInstallCallback) {
        install(
            cmd = "magisk --install-module '${path}'",
            path = path,
            callback = callback
        )
    }
}