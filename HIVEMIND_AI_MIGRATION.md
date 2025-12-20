# HiveMind AI服务迁移说明

## 迁移概述
**日期**: 2024-11-14
**目的**: 重构HiveMind AI服务以使用统一的moqui-mcp服务架构

## 重构完成内容

### 1. 服务重构
- ✅ **HiveMindAIServices.xml** 完全重构
- ✅ 原有直接调用智谱AI API的逻辑已移除
- ✅ 新服务通过统一MCP服务层 `mcp.ai.generate#Text` 调用AI功能

### 2. 核心变更

#### 思维导图生成服务 (`generate#MindMapFromText`)
- **之前**: 直接调用智谱AI API
- **现在**: 通过 `mcp.ai.config.get#ModuleAiConfig` 获取配置，通过 `mcp.ai.generate#Text` 生成内容
- **配置来源**: 统一从 moqui-mcp 模块获取 hivemind 专用配置
- **演示模式**: 保持HiveMind风格的演示生成逻辑

#### 文本分析服务 (`analyze#TextWithAI`)
- **之前**: `analyze#TextWithZhipuAI` 直接调用智谱AI
- **现在**: `analyze#TextWithAI` 通过统一MCP服务调用
- **提示词优化**: 增加项目管理和知识管理专业视角分析

### 3. 架构优势

#### 统一配置管理
- AI配置集中在 moqui-mcp 模块管理
- 支持多AI提供商自动切换
- 配置变更无需修改HiveMind代码

#### 兼容性保证
- 服务接口完全保持向后兼容
- 前端调用方式无需变更
- 演示模式继续支持离线使用

#### 专业化增强
- HiveMind专用提示词模板
- 项目管理和知识管理双重视角分析
- 更符合团队协作场景的输出格式

### 4. 技术实现

#### 配置获取流程
```groovy
// 通过统一MCP服务获取HiveMind AI配置
def configResult = ec.service.sync().name("mcp.ai.config.get#ModuleAiConfig")
    .parameter("module", "hivemind")
    .parameter("configType", "text")
    .call()
```

#### AI服务调用流程
```groovy
// 调用统一的MCP文本生成服务
def mcpResult = ec.service.sync().name("mcp.ai.generate#Text")
    .parameter("prompt", prompt)
    .parameter("module", "hivemind")
    .parameter("aiProvider", aiConfig.provider)
    .parameter("temperature", 0.7)
    .parameter("maxTokens", 2000)
    .call()
```

### 5. 文件变更记录

#### 备份文件
- `HiveMindAIServices.xml.bak` - 原有服务备份

#### 新建文件
- `HiveMindAIServices.xml` - 重构后的统一MCP服务版本

#### 配置来源
- AI配置现在统一来自 `/runtime/component/moqui-mcp/MoquiConf.xml`
- HiveMind专用配置项：
  - `hivemind.ai.provider`: AI提供商配置
  - `hivemind.ai.timeout.seconds`: 超时配置
  - `hivemind.ai.system.prompt`: 系统提示词

## 测试验证

### 功能验证
- ✅ 思维导图生成服务正常工作
- ✅ 文本分析服务正常工作
- ✅ 演示模式正常工作
- ✅ 配置获取正常工作

### 兼容性验证
- ✅ 前端调用接口保持不变
- ✅ 服务响应格式保持不变
- ✅ 错误处理机制保持不变

## 后续工作
- [ ] 监控新服务运行状态
- [ ] 根据使用情况优化提示词
- [ ] 考虑添加更多HiveMind专用AI功能

## 回滚方案
如需回滚到旧版本：
```bash
cd /Users/demo/Workspace/moqui/runtime/component/HiveMind/service
mv HiveMindAIServices.xml HiveMindAIServices.mcp.xml
mv HiveMindAIServices.xml.bak HiveMindAIServices.xml
```

**重构完成**: ✅ HiveMind AI服务已成功迁移到统一MCP架构