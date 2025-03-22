package com.example.testapplication

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.util.Log

class MyAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return

        // 获取当前界面的根节点
        val rootNode = rootInActiveWindow ?: return

        // 解析 UI 结构
        parseUI(rootNode)
    }

    override fun onInterrupt() {
        // 处理服务被中断的情况
    }

    private fun parseUI(node: AccessibilityNodeInfo) {
        if (node.className != null) {
            Log.d("UI Element", "Class: ${node.className}, Text: ${node.text}")
        }

        // 遍历所有子节点
        for (i in 0 until node.childCount) {
            node.getChild(i)?.let { parseUI(it) }
        }
    }
}