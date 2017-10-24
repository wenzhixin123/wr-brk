package com.sinotrans.framework.core.util;


/**
 * DES算法理论

本世纪五十年代以来，密码学研究领域出现了最具代表性的两大成就。其中之一就是1971年美国学者塔奇曼 （Tuchman）和麦耶（Meyer）根据信息论创始人香农（Shannon）提出的「多重加密有效性理论」创立的，后於1977年由美国国家标準局颁布的数据加密标準。 
DES密码实际上是Lucifer密码的进一步发展。它是一种採用传统加密方法的区组密码。它的算法是对称的，既可用於加密又可用於解密。

美国国家标準局1973年开始研究除国防部外的其它部门的计算机系统的数据加密标準，於1973年5月15日和1974年8月27日先后两次向公眾发出了徵求加密算法的公告。 加密算法要达到的目的通常称為DES密码算法要求主要為以下四点：

提供高质量的数据保护，防止数据未经授权的洩露和未被察觉的修改；具有相当高的复杂性，使得破译的开销超过可能获得的利益，同时又要便於理解和掌握 DES密码体制的安全性应该不依赖於算法的保密，其安全性仅以加密密钥的保密為基础实现经济，运行有效，并且适用於多种完全不同的应用。

1977年1月，美国政府颁布：採纳IBM公司设计的方案作為非机密数据的正式数据加密标準（DES枣Data Encryption Standard）。

　　目前在这裡，随著三金工程尤其是金卡工程的啟动，DES算法在POS、ATM、磁卡及智能卡（IC卡）、加油站、高速公路收费站等领域被广泛应用，以此来实现关键数据的保密，如信用卡持卡人的PIN的加密传输，IC卡与POS间的双向认证、金融交易数据包的MAC校验等，均用到DES算法。

　　DES算法的入口参数有三个：Key、Data、Mode。其中Key為8个字节共64位，是DES算法的工作密钥；Data也為8个字节64位，是要被加密或被解密的数据；Mode為DES的工作方式，有两种：加密或解密。

　　DES算法是这样工作的：如Mode為加密，则用Key 去把数据Data进行加密， 生成Data的密码形式（64位）作為DES的输出结果；如Mode為解密，则用Key去把密码形式的数据Data解密，还原為Data的明码形式（64位）作為DES的输出结果。在通信网络的两端，双方约定一致的Key，在通信的源点用Key对核心数据进行DES加密，然后以密码形式在公共通信网（如电话网）中传输到通信网络的终点，数据到达目的地后，用同样的Key对密码数据进行解密，便再现了明码形式的核心数据。这样，便保证了核心数据（如PIN、MAC等）在公共通信网中传输的安全性和可靠性。

　　通过定期在通信网络的源端和目的端同时改用新的Key，便能更进一步提高数据的保密性，这正是现在金融交易网络的流行做法。

　　DES算法详述

　　DES算法把64位的明文输入块变為64位的密文输出块，它所使用的密钥也是64位,其功能是把输入的64位数据块按位重新组合，并把输出分為L0、R0两部分，每部分各长32位，其置换规则见下表：

   58,50,12,34,26,18,10,2,60,52,44,36,28,20,12,4,
　　62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,
　　57,49,41,33,25,17, 9,1,59,51,43,35,27,19,11,3,
　　61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7,


　　即将输入的第58位换到第一位，第50位换到第2位，...，依此类推，最后一位是原来的第7位。L0、R0则是换位输出后的两部分，L0是输出的左32位，R0 是右32位，例：设置换前的输入值為D1D2D3......D64，则经过初始置换后的结果為：L0=D550...D8；R0=D57D49...D7。

　　经过26次迭代运算后。得到L16、R16，将此作為输入，进行逆置换，即得到密文输出。逆置换正好是初始置的逆运算，例如，第1位经过初始置换后，处於第40位，而通过逆置换，又将第40位换回到第1位，其逆置换规则如下表所示：

　　40,8,48,16,56,24,64,32,39,7,47,15,55,23,63,31,
　　38,6,46,14,54,22,62,30,37,5,45,13,53,21,61,29,
　　36,4,44,12,52,20,60,28,35,3,43,11,51,19,59,27,
　　34,2,42,10,50,18,58 26,33,1,41, 9,49,17,57,25,

放大换位表

　　32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10,11,
　　12,13,12,13,14,15,16,17,16,17,18,19,20,21,20,21,
　　22,23,24,25,24,25,26,27,28,29,28,29,30,31,32, 1,

单纯换位表

　　16,7,20,21,29,12,28,17, 1,15,23,26, 5,18,31,10,
　　2,8,24,14,32,27, 3, 9,19,13,30, 6,22,11, 4,25,

　　在f(Ri,Ki)算法描述图中，S1,S2...S8為选择函数，其功能是把6bit数据变為4bit数据。下面给出选择函数Si(i=1,2......8)的功能表：
选择函数Si

S1:
　　14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7,
　　0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8,
　　4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0,
　　15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13,
S2:
　　15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10,
　　3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5,
　　0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15,
　　13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9,
S3:
　　10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8,
　　13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1,
　　13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7,
　　1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12,
S4:
　　7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15,
　　13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9,
　　10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4,
　　3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14,
S5:
　　2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9,
　　14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6,
　　4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14,
　　11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3,
S6:
　　12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11,
　　10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8,
　　9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6,
　　4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13,
S7:
　　4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1,
　　13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6,
　　1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2,
　　6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12,
S8:
　　13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7,
　　1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2,
　　7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8,
　　2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11,


在此以S1為例说明其功能，我们可以看到：在S1中，共有4行数据，命名為0，1、2、3行；每行有16列，命名為0、1、2、3，......，14、15列。

　　现设输入為： D＝D1D2D3D4D5D6
令：列＝D2D3D4D5
　　行＝D1D6

　　然后在S1表中查得对应的数，以4位二进製表示，此即為选择函数S1的输出。下面给出子密钥Ki(48bit)的生成算法

　　从子密钥Ki的生成算法描述图中我们可以看到：初始Key值為64位，但DES算法规定，其中第8、16、......64位是奇偶校验位，不参与DES运算。故Key 实际可用位数便只有56位。即：经过缩小选择换位表1的变换后，Key 的位数由64 位变成了56位，此56位分為C0、D0两部分，各28位，然后分别进行第1次循环左移，得到C1、D1，将C1（28位）、D1（28位）合併得到56位，再经过缩小选择换位2，从而便得到了密钥K0（48位）。依此类推，便可得到K1、K2、......、K15，不过需要注意的是，16次循环左移对应的左移位数要依据下述规则进行：

循环左移位数
1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1
　　以上介绍了DES算法的加密过程。DES算法的解密过程是一样的，区别仅仅在於第一次迭代时用子密钥K15，第二次K14、......，最后一次用K0，算法本身并没有任何变化。
DES算法具有极高安全性，到目前為止，除了用穷举搜索法对DES算法进行攻击外，还没有发现更有效的办法。而56位长的密钥的穷举空间為256，这意味著如果一台计算机的速度是每一秒种检测一百万个密钥，则它搜索完全部密钥就需要将近2285年的时间，可见，这是难以实现的，当然，随著科学技术的发展，当出现超高速计算机后，我们可考虑把DES密钥的长度再增长一些，以此来达到更高的保密程度。

　　由上述DES算法介绍我们可以看到：DES算法中只用到64位密钥中的其中56位，而第8、16、24、......64位8个位并未参与DES运算，这一点，向我们提出了一个应用上的要求，即DES的安全性是基於除了8，16，24，......64位外的其餘56位的组合变化256才得以保证的。因此，在实际应用中，我们应避开使用第8，16，24，......64位作為有效数据位，而使用其它的56位作為有效数据位，才能保证DES算法安全可靠地发挥作用。如果不瞭解这一点，把密钥Key的8，16，24，..... .64位作為有效数据使用，将不能保证DES加密数据的安全性，对运用DES来达到保密作用的系统產生数据被破译的危险，这正是DES算法在应用上的误区，是各级技术人员、各级领导在使用过程中应绝对避免的，而当今各金融部门及非金融部门，在运用DES工作，掌握DES工作密钥Key的领导、主管们，极易忽略，给使用中貌似安全的系统，留下了被人攻击、被人破译的极大隐患。
DES算法应用误区的验证数据

　　笔者用Turbo C编写了DES算法程序，并在PC机上对上述的DES 算法的应用误区进行了騅，其验证数据如下：

Key: 0x30 0x30 0x30 0x30......0x30（8个字节）
Data: 0x31 0x31 0x31 0x31......0x31（8个字节）
Mode: Encryption
结果：65 5e a6 28 cf 62 58 5f

　　如果把上述的Key换為8个字节的0x31，而Data和Mode均不变，则执行DES 后得到的密文完全一样。类似地，用Key:8个0x32和用Key:8个0x33 去加密Data （8 个0x31），二者的图文输出也是相同的：5e c3 ac e9 53 71 3b ba
我们可以得到出结论：
Key用0x30与用0x31是一样的；
Key用0x32与用0x33是一样的，......

　　当Key由8个0x32换成8个0x31后，貌似换成了新的Key，但由於0x30和0x31仅仅是在第8，16，24......64有变化，而DES算法并不使用Key的第8，16，......64位作為Key的有效数据位，故：加密出的结果是一样的。
DES解密的验证数据：

Key: 0x31 0x31......0x31（8个0x31）
Data: 65 5e a6 28 cf 62 58 5f
Mode: Decryption
结果：0x31 0x31......0x31（8个0x31）

　　由以上看出：DES算法加密与解密均工作正确。唯一需要避免的是：在应用中，避开使用Key的第8，16......64位作為有效数据位，从而便避开了DES 算法在应用中的误区。
避开DES算法应用误区的具体操作

　　在DES密钥Key的使用、管理及密钥更换的过程中，应绝对避开DES 算法的应用误区，即：绝对不能把Key的第8，16，24......64位作為有效数据位，来对Key 进行管理。这一点，特别推荐给金融银行界及非金融业界的领导及决策者们，尤其是负责管理密钥的人，要对此点予以高度重视。有的银行金融交易网络，利用定期更换DES密钥Key的办法来进一步提高系统的安全性和可靠性，如果忽略了上述应用误区，那麼，更换新密钥将是徒劳的，对金融交易网络的安全运行将是十分危险的，所以更换密钥一定要保证新Key与旧Key真正的不同，即除了第8，16，24，...64位外其它位数据发生了变化，请务必对此保持高度重视！

 * @author zy20022630
 */
/**
 	本类实现的是基本数据类型上的算法运算
 */
public class DES {
	//密钥
	private String key;
	//为了字节数组与字符串互换而使用的
    private static final String BASE64_CHARS ="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz*-";
    private static final char[] BASE64_CHARSET = BASE64_CHARS.toCharArray();
    // 声明常量字节数组
	/**
	 * DES算法把64位的明文输入块变為64位的密文输出块，它所使用的密钥也是64位,
	 * 其功能是把输入的64位数据块按位重新组合，
	 * 并把输出分為L0、R0两部分，每部分各长32位，其置换规则见下表
	 * 即将输入的第58位换到第一位，第50位换到第2位，...，依此类推，
	 * 最后一位是原来的第7位。L0、R0则是换位输出后的两部分，
	 * L0是输出的左32位，R0 是右32位，
	 * 例：设置换前的输入值為D1D2D3......D64，
	 * 则经过初始置换后的结果為：L0=D550...D8；R0=D57D49...D7
	 */
    private static final int[] IP = {  //OK
            58, 50, 42, 34, 26, 18, 10, 2, 
            60, 52, 44, 36, 28, 20, 12, 4, 
            62, 54, 46, 38, 30, 22, 14, 6, 
            64, 56, 48, 40, 32, 24, 16, 8, 
            57, 49, 41, 33, 25, 17, 9,  1, 
            59, 51, 43, 35, 27, 19, 11, 3, 
            61, 53, 45, 37, 29, 21, 13, 5, 
            63, 55, 47, 39, 31, 23, 15, 7
        }; // 64
    /**
     * 经过26次迭代运算后。得到L16、R16，将此作為输入，进行逆置换，即得到密文输出。
     * 逆置换正好是初始置的逆运算，例如，第1位经过初始置换后，处於第40位，
     * 而通过逆置换，又将第40位换回到第1位，其逆置换规则如下表所示：
     */
    private static final int[] IP_1 = {  //OK
            40, 8, 48, 16, 56, 24, 64, 32, 
            39, 7, 47, 15, 55, 23, 63, 31, 
            38, 6, 46, 14, 54, 22, 62, 30, 
            37, 5, 45, 13, 53, 21, 61, 29, 
            36, 4, 44, 12, 52, 20, 60, 28, 
            35, 3, 43, 11, 51, 19, 59, 27, 
            34, 2, 42, 10, 50, 18, 58, 26, 
            33, 1, 41, 9,  49, 17, 57, 25
        }; // 64
    /**
     * PC1置换
     */
    private static final int[] PC_1 = {
            57, 49, 41, 33, 25, 17, 9,  
             1, 58, 50, 42, 34, 26, 18, 
            10,  2, 59, 51, 43, 35, 27, 
            19, 11,  3, 60, 52, 44, 36, 
            63, 55, 47, 39, 31, 23, 15,  
             7, 62, 54, 46, 38, 30, 22, 
            14,  6, 61, 53, 45, 37, 29, 
            21, 13,  5, 28, 20, 12,  4
        }; // 56
    /**
     * PC2置换
     */
    private static final int[] PC_2 = {
            14, 17, 11, 24,  1,  5, 
             3, 28, 15,  6, 21, 10, 
            23, 19, 12,  4, 26,  8, 
            16,  7, 27, 20, 13,  2, 
            41, 52, 31, 37, 47, 55, 
            30, 40, 51, 45, 33, 48, 
            44, 49, 39, 56, 34, 53, 
            46, 42, 50, 36, 29, 32
        }; // 48
    /**
     * 放大换位表
     */
    private static final int[] E = {  //OK
            32,  1,  2,  3,  4,  5, 
            4,   5,  6,  7,  8,  9, 
            8,   9, 10, 11, 12, 13, 
            12, 13, 14, 15, 16, 17, 
            16, 17, 18, 19, 20, 21, 
            20, 21, 22, 23, 24, 25, 
            24, 25, 26, 27, 28, 29, 
            28, 29, 30, 31, 32,  1
        }; // 48
    /**
     * 单纯换位表
     */
    private static final int[] P = { //OK
            16,  7, 20, 21, 
            29, 12, 28, 17,
             1, 15, 23, 26, 
             5, 18, 31, 10, 
             2,  8, 24, 14, 
             32, 27, 3,  9, 
             19, 13, 30, 6, 
             22, 11,  4, 25
        }; // 32
    /**
     * 在f(Ri,Ki)算法描述图中，S1,S2...S8為选择函数，其功能是把6bit数据变為4bit数据。
     * 下面给出选择函数Si(i=1,2......8)的功能表：选择函数Si
     */
    private static final int[][][] S_Box = {
            {// S_Box[1]   OK
                { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
                { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
                { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
                { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 }
            },
            { // S_Box[2]  OK
                { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
                { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
                { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
                { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 }
            },
            { // S_Box[3]  OK
                { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
                { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
                { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
                { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 }
            },
            { // S_Box[4]  OK
                { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
                { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
                { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
                { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 }
            },
            { // S_Box[5] OK
                { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
                { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
                { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
                { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 }
            },
            { // S_Box[6] OK
                { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
                { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
                { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
                { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 }
            },
            { // S_Box[7] OK
                { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
                { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
                { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
                { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 }
            },
            { // S_Box[8] OK
                { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
                { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
                { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
                { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 }
            } 
        };
    /**
     * 循环左移位数 1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1
     */
    private static final int[] LeftMove = {
            1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
        }; // 左移位置列表

    //构造函数，初始化密钥
    public DES(String key){
    	this.key = key;
    }
    
    /**
     * 
     * @param des_key 8个字节的密钥字节数组
     * @param des_data 8个字节的数据字节数组
     * @param flag 1或0，1为加密，0为解密
     * @return 8个字节的字节数组
     */
    private byte[] UnitDes(byte[] des_key, byte[] des_data, int flag) {
        // 检测输入参数格式是否正确，错误直接返回空值（null）
        if ((des_key.length != 8) || (des_data.length != 8) || ((flag != 1) && (flag != 0))) {
            throw new RuntimeException("Data Format Error !");
        }
        int flags = flag;
        // 二进制加密密钥
        int[] keydata = new int[64];
        // 二进制加密数据
        int[] encryptdata = new int[64];
        // 加密操作完成后的字节数组
        byte[] EncryptCode = new byte[8];
        // 密钥初试化成二维数组
        int[][] KeyArray = new int[16][48];
        // 将密钥字节数组转换成二进制字节数组
        keydata = ReadDataToBirnaryIntArray(des_key);
        // 将加密数据字节数组转换成二进制字节数组
        encryptdata = ReadDataToBirnaryIntArray(des_data);
        // 初试化密钥为二维密钥数组
        KeyInitialize(keydata, KeyArray);
        // 执行加密解密操作
        EncryptCode = Encrypt(encryptdata, flags, KeyArray);
        return EncryptCode;
    }

    /**
     * 初试化密钥为二维密钥数组
     * @param key int[64]二进制的密钥数组
     * @param keyarray new int[16][48]
     */
    private void KeyInitialize(int[] key, int[][] keyarray) {
        int i;
        int j;
        int[] K0 = new int[56];
        // 特别注意：xxx[IP[i]-1]等类似变换
        for (i = 0; i < 56; i++) {
            K0[i] = key[PC_1[i] - 1]; // 密钥进行PC-1变换
        }
        for (i = 0; i < 16; i++) {
            LeftBitMove(K0, LeftMove[i]);
            // 特别注意：xxx[IP[i]-1]等类似变换
            for (j = 0; j < 48; j++) {
                keyarray[i][j] = K0[PC_2[j] - 1]; // 生成子密钥keyarray[i][j]
            }
        }
    }

    /**
     * 执行加密解密操作
     * @param timeData (int[64])二进制加密数据
     * @param flag 1或0，1为加密，0为解密
     * @param keyarray new int[16][48]
     * @return 长度为8的字节数组
     */
    private byte[] Encrypt(int[] timeData, int flag, int[][] keyarray) {
        int i;
        byte[] encrypt = new byte[8];
        int flags = flag;
        int[] M = new int[64];
        int[] MIP_1 = new int[64];
        // 特别注意：xxx[IP[i]-1]等类似变换
        for (i = 0; i < 64; i++) {
            M[i] = timeData[IP[i] - 1]; // 明文IP变换
        }
        if (flags == 1) { // 加密
            for (i = 0; i < 16; i++) {
                LoopF(M, i, flags, keyarray);//S盒处理
            }
        } else if (flags == 0) { // 解密
            for (i = 15; i > -1; i--) {
                LoopF(M, i, flags, keyarray);//S盒处理
            }
        }
        for (i = 0; i < 64; i++) {
            MIP_1[i] = M[IP_1[i] - 1]; // 进行IP-1运算
        }
        //将（int[64]二进制数据字节数组，经过IP、S盒、IP-1处理后，得到的新的）int[64]二进制数据字节数组转换成byte[8]的字节数组
        GetEncryptResultOfByteArray(MIP_1, encrypt);
        // 返回加密数据
        return encrypt;
    }

    /**
     * 转换8个字节长度的数据字节数组为二进制数组
     * (一个字节转换为8个二进制)
     * @param intdata 8个字节的数据字节数组
     * @return 长度为64的二进制数组
     */
    private int[] ReadDataToBirnaryIntArray(byte[] intdata) {
        int i;
        int j;
        // 将数据转换为二进制数，存储到数组
        int[] IntDa = new int[8];
        for (i = 0; i < 8; i++) {
            IntDa[i] = intdata[i];//intdata[i]为byte,范围是-128~127
            if (IntDa[i] < 0) {//故：IntDa[i]范围是-128~127
                IntDa[i] += 256;//IntDa[i]永远不会超过256
                IntDa[i] %= 256;//所以该处不需要取模，取模后结果还是自己
            }
        }
        int[] IntVa = new int[64];
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                IntVa[((i * 8) + 7) - j] = IntDa[i] % 2;
                IntDa[i] = IntDa[i] / 2;
            }
        }
        return IntVa;
    }
    
    /**
     * int[64]二进制数据字节数组转换成byte[8]的字节数组
     * @param data int[64]二进制数据字节数组
     * @param value byte[8] byte[8]的字节数组
     */
    private void GetEncryptResultOfByteArray(int[] data, byte[] value) {
        int i;
        int j;
        // 将存储64位二进制数据的数组中的数据转换为八个整数（byte）
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                value[i] += (byte)(data[(i << 3) + j] << (7 - j));
            }
        }
        for (i = 0; i < 8; i++) {
            value[i] %= 256;
            if (value[i] > 128) {
                value[i] -= 255;
            }
        }
    }

    /**
     * 左移
     * @param k
     * @param offset
     */
    private void LeftBitMove(int[] k, int offset) {
        int i;
        // 循环移位操作函数
        int[] c0 = new int[28];
        int[] d0 = new int[28];
        int[] c1 = new int[28];
        int[] d1 = new int[28];
        for (i = 0; i < 28; i++) {
            c0[i] = k[i];
            d0[i] = k[i + 28];
        }
        if (offset == 1) {
            for (i = 0; i < 27; i++) { // 循环左移一位
                c1[i] = c0[i + 1];
                d1[i] = d0[i + 1];
            }
            c1[27] = c0[0];
            d1[27] = d0[0];
        } else if (offset == 2) {
            for (i = 0; i < 26; i++) { // 循环左移两位
                c1[i] = c0[i + 2];
                d1[i] = d0[i + 2];
            }
            c1[26] = c0[0];
            d1[26] = d0[0];
            c1[27] = c0[1];
            d1[27] = d0[1];
        }
        for (i = 0; i < 28; i++) {
            k[i] = c1[i];
            k[i + 28] = d1[i];
        }
    }

    /**
     * S盒处理
     * @param M
     * @param times
     * @param flag
     * @param keyarray
     */
    private void LoopF(int[] M, int times, int flag, int[][] keyarray) {
        int i;
        int j;
        int[] L0 = new int[32];
        int[] R0 = new int[32];
        int[] L1 = new int[32];
        int[] R1 = new int[32];
        int[] RE = new int[48];
        int[][] S = new int[8][6];
        int[] sBoxData = new int[8];
        int[] sValue = new int[32];
        int[] RP = new int[32];
        for (i = 0; i < 32; i++) {
            L0[i] = M[i]; // 明文左侧的初始化
            R0[i] = M[i + 32]; // 明文右侧的初始化
        }
        for (i = 0; i < 48; i++) {
            RE[i] = R0[E[i] - 1]; // 经过E变换扩充，由32位变为48位
            RE[i] = RE[i] + keyarray[times][i]; // 与KeyArray[times][i]按位作不进位加法运算
            if (RE[i] == 2) {
                RE[i] = 0;
            }
        }
        for (i = 0; i < 8; i++) { // 48位分成8组
            for (j = 0; j < 6; j++) {
                S[i][j] = RE[(i * 6) + j];
            }
            // 下面经过S盒，得到8个数
            sBoxData[i] = S_Box[i][(S[i][0] << 1) + S[i][5]][(S[i][1] << 3) +
                (S[i][2] << 2) + (S[i][3] << 1) + S[i][4]];
            // 8个数变换输出二进制
            for (j = 0; j < 4; j++) {
                sValue[((i * 4) + 3) - j] = sBoxData[i] % 2;
                sBoxData[i] = sBoxData[i] / 2;
            }
        }
        for (i = 0; i < 32; i++) {
            RP[i] = sValue[P[i] - 1]; // 经过P变换
            L1[i] = R0[i]; // 右边移到左边
            R1[i] = L0[i] + RP[i];
            if (R1[i] == 2) {
                R1[i] = 0;
            }
            // 重新合成M，返回数组M
            // 最后一次变换时，左右不进行互换。此处采用两次变换实现不变
            if (((flag == 0) && (times == 0)) || ((flag == 1) && (times == 15))) {
                M[i] = R1[i];
                M[i + 32] = L1[i];
            } else {
                M[i] = L1[i];
                M[i + 32] = R1[i];
            }
        }
    }
    
    /**
     * 把一个字节数组的元素拷贝到另一个字节数组中
     * @param src
     * @param srcPos
     * @param dest
     * @param destPos
     * @param length
     */
    private void arraycopy(byte[] src,int srcPos,byte[] dest, int destPos,int length){
    	if (dest != null && src != null){//当两个都不为空时
    		byte[] temp = new byte[length];
    		for (int i = 0;i < length;i++){
    			temp[i] = src[srcPos + i];
    		}
    		for (int i = 0;i < length;i++){
    			dest[destPos + i] = temp[i];
    		}
    	}
    }

    /**
     * 格式化字节数组，使其的长度为8的倍数，那些不足的部分元素用0填充
     * @return 一个新的字节数组，其长度比原数组长1-8位
     */
    private byte[] ByteDataFormat(byte[] data) {
        int len = data.length;
        int padlen = 8 - (len % 8);//要格式化的字节数组的长度与8的倍数的差值
        int newlen = len + padlen;
        byte[] newdata = new byte[newlen];
        arraycopy(data, 0, newdata, 0, len);
        for (int i = len; i < newlen; i++)
            newdata[i] = 0;
        return newdata;
    }

    /**
     * 加密解密(主要方法)
     * @param des_key 密钥字节数组
     * @param des_data 要处理的数据字节数组
     * @param flag (1或0)，1为加密，0为解密
     * @return 处理后的数据
     */
    private byte[] DesEncrypt(byte[] des_key, byte[] des_data, int flag) {
        byte[] format_key = ByteDataFormat(des_key);//补齐密钥字节数组的长度为8的倍数，不足元素用0补
        byte[] format_data = ByteDataFormat(des_data);//补齐原始数据字节数组的长度为8的倍数，不足元素用0补
        int datalen = format_data.length;//补齐后的原始数据字节数组的长度
        int unitcount = datalen / 8;//补齐后的原始数据字节数组长度是8的多少倍
        byte[] result_data = new byte[datalen];//用于盛放加密后的结果
        //每一次循环，都操作8个字节（加密解密）
        for (int i = 0; i < unitcount; i++) {
            byte[] tmpkey = new byte[8];//真正起作用的密钥字节数组，只有8个字节
            byte[] tmpdata = new byte[8];//用于参与操作的数据字节数组，只有8个字节
            arraycopy(format_key, 0, tmpkey, 0, 8);
            arraycopy(format_data, i * 8, tmpdata, 0, 8);
            byte[] tmpresult = UnitDes(tmpkey, tmpdata, flag);//执行操作
            arraycopy(tmpresult, 0, result_data, i * 8, 8);
        }
        return result_data;
    }
    
    /**
     * DES加密
     * @param data 原始数据(长度不能超过9999位)
     * @return 加密后的数据字节数组
     */
    public String encrypt(String data){
    	String dataLength = data.length() + "";//原始数据长度
    	//原始数据长度不满四位的加0补满四位
    	if (dataLength.length() == 1){
    		dataLength = "000" + dataLength;
    	}else if (dataLength.length() == 2){
    		dataLength = "00" + dataLength;
    	}if (dataLength.length() == 3){
    		dataLength = "0" + dataLength;
    	}
    	String sbDate = dataLength + data;//保证原始数据的前4位为该数据的长度
    	byte[] bytekey = key.getBytes();//密钥字节数组
        byte[] bytedata = sbDate.getBytes();//原始数据字节数组
        byte[] result = new byte[(bytedata.length + 8) - (bytedata.length % 8)];//能包含原始数据字节数组的长度是8的倍数的最小字节数组
    	result = DesEncrypt(bytekey, bytedata, 1);
    	return toBase64(result);
    }
    
    /**
     * DES解密
     * @param encryptData 加密后的数据字节数组
     * @return 还原后的数据字符串
     */
    public String decrypt(String encryptData){
    	try {
    		byte[] encryptByteArray = fromBase64(encryptData);
			byte[] bytekey = key.getBytes();
	    	byte[] result = new byte[encryptByteArray.length];
	    	result = DesEncrypt(bytekey, encryptByteArray, 0);
			String deResult = new String(result, 0, result.length, "UTF-8");
	    	int dataLength = Integer.parseInt(deResult.substring(0, 4));
	        return deResult.substring(4, dataLength+4);
		} catch (Exception e) {
			return "";
		}
    }
    
    /**
     * 字节数组转换为字符串
     * @param buffer
     * @return
     */
    private String toBase64(byte[] buffer) {
	      int len = buffer.length, pos = len % 3;
	      byte b0 = 0, b1 = 0, b2 = 0;
	      switch (pos) {
	      case 1:
	         b2 = buffer[0];
	         break;
	      case 2:
	         b1 = buffer[0];
	         b2 = buffer[1];
	         break;
	      }
	      String returnValue = "";
	      int c;
	      boolean notleading = false;
	      do {
//	         c = (b0 & 0xFC) >>> 2;
	    	 c = (b0 & 0xFC) >> 2;
	         if (notleading || c != 0) {
	           returnValue += BASE64_CHARSET[c];
	           notleading = true;
	         }
//	         c = ((b0 & 0x03) << 4) | ((b1 & 0xF0) >>> 4);
	         c = ((b0 & 0x03) << 4) | ((b1 & 0xF0) >> 4);
	         if (notleading || c != 0) {
	           returnValue += BASE64_CHARSET[c];
	           notleading = true;
	         }
//	         c = ((b1 & 0x0F) << 2) | ((b2 & 0xC0) >>> 6);
	         c = ((b1 & 0x0F) << 2) | ((b2 & 0xC0) >> 6);
	         if (notleading || c != 0) {
	           returnValue += BASE64_CHARSET[c];
	           notleading = true;
	         }
	         c = b2 & 0x3F;
	         if (notleading || c != 0) {
	           returnValue += BASE64_CHARSET[c];
	           notleading = true;
	         }
	         if (pos >= len) {
	           break;
	         } else {
	           try {
	             b0 = buffer[pos++];
	             b1 = buffer[pos++];
	             b2 = buffer[pos++];
	           } catch (Exception x) {
	             break;
	           }
	         }
	      } while (true);

	      if (notleading) {
	        return returnValue;
	      }
	      return "0";
	   }
    
    /**
     * 字符串转换为字节数组
     * @param str
     * @return
     * @throws Exception 
     */
    private  byte[] fromBase64(String str) throws Exception {
	      int len = str.length();
	      if (len == 0) {
	         throw new Exception("Empty string");
	      }
	      byte[] a = new byte[len + 1];
	      int i, j;
	      for (i = 0; i < len; i++) {
	         try {
	            a[i] = (byte) BASE64_CHARS.indexOf(str.charAt(i));
	         } catch (Exception x) {
	            throw new Exception("Illegal character at #"+i);
	         }
	      }
	      i = len - 1;
	      j = len;
	      try {
	         while (true) {
	            a[j] = a[i];
	            if (--i < 0) {
	               break;
	            }
	            a[j] |= (a[i] & 0x03) << 6;
	            j--;
//	            a[j] = (byte)((a[i] & 0x3C) >>> 2);
	            a[j] = (byte)((a[i] & 0x3C) >> 2);
	            if (--i < 0) {
	               break;
	            }
	            a[j] |= (a[i] & 0x0F) << 4;
	            j--;
//	            a[j] = (byte)((a[i] & 0x30) >>> 4);
	            a[j] = (byte)((a[i] & 0x30) >> 4);
	            if (--i < 0) {
	               break;
	            }
	            a[j] |= (a[i] << 2);
	            j--;
	            a[j] = 0;
	            if (--i < 0) {
	               break;
	            }
	         }
	      } catch (Exception ignored) {
	      }

	      try { // ignore leading 0-bytes
	         while(a[j] == 0) {
	            j++;
	         }
	      } catch (Exception x) {
	         return new byte[1]; // one 0-byte
	      }
	      byte[] result = new byte[len - j + 1];
	      arraycopy(a, j, result, 0, len - j + 1);
	      return result;
	   }
    

}