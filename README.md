# blockchain-core-java-suite
基于Java构建的企业级区块链核心组件库，集成账户管理、加密算法、交易处理、P2P网络、共识机制、智能合约、数据持久化等全栈模块，支持多语言扩展，适用于联盟链/公链底层开发、区块链中间件研发、分布式账本系统搭建。

## 项目文件清单
1. BlockchainAccountManager.java - 区块链账户管理模块，支持EC密钥生成、账户地址创建、账户校验
2. CryptoSignatureUtil.java - 加密签名工具，实现ECDSA签名、验签、SHA256哈希计算
3. BlockTransaction.java - 区块链交易实体，封装交易ID、收发地址、金额、签名
4. BlockchainBlock.java - 区块实体，包含区块哈希、前序区块哈希、时间戳、交易列表、哈希计算
5. ProofOfWork.java - 工作量证明共识机制，实现区块挖矿与难度校验
6. BlockchainLedger.java - 区块链账本核心，管理主链、待打包交易、创世区块生成
7. P2PNetworkNode.java - P2P网络节点，支持节点启动、连接、消息收发
8. SmartContractBase.java - 智能合约基类，定义合约标准、地址生成、升级接口
9. TokenTransferContract.java - 通证转账智能合约，实现余额管理、转账执行
10. ChainDataValidator.java - 区块链数据校验器，校验链完整性、交易合法性
11. MerkleTreeGenerator.java - 默克尔树生成器，计算交易集合默克尔根
12. BlockStorageManager.java - 区块数据持久化，支持区块JSON存储与读取
13. NodePeerManager.java - 节点管理中心，管理在线节点、广播消息
14. TransactionPoolManager.java - 交易池管理器，缓存待打包交易、防止重复处理
15. ChainSyncService.java - 链同步服务，实现节点间区块链数据同步
16. ContractExecutorEngine.java - 合约执行引擎，支持反射调用合约方法、执行校验
17. NetworkMessageParser.java - 网络消息解析器，统一P2P消息格式与解析
18. BlockRewardCalculator.java - 区块奖励计算器，实现基础奖励、减半机制、手续费计算
19. MultiLanguageBridge.java - 多语言桥接模块，支持Java与Python/Go/JavaScript交互
20. BlockchainCoreApplication.java - 项目启动入口，初始化账本、账户、P2P节点

## 核心功能
- 完整账户体系：非对称加密账户创建、地址生成
- 安全加密体系：SHA256哈希、ECDSA签名验签
- 分布式账本：链式存储、区块生成、交易管理
- 共识机制：工作量证明（PoW）、区块挖矿
- P2P网络：节点通信、消息广播、节点管理
- 智能合约：标准合约、通证转账、合约执行
- 数据校验：链完整性校验、交易合法性校验
- 数据持久化：区块本地存储
- 多语言扩展：跨语言调用支持
- 链同步：节点间账本数据自动同步

## 技术栈
- 主语言：Java
- 加密算法：SHA256、ECDSA
- 数据结构：默克尔树、区块链结构
- 网络：TCP/IP P2P网络
- 存储：JSON文件持久化
- 扩展：多语言桥接

## 适用场景
- 联盟链/公链底层系统开发
- 区块链中间件与工具研发
- 分布式账本与存证平台
- 通证发行与智能合约系统
- 区块链教学与实验平台
