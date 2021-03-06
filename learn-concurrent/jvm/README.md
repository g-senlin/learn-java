This module record jvm knowledge.

**内容目录**

[TOC]  

## JVM（Java 虚拟机） 

### Java 内存区域

#### 程序计数器（Program Counter Register）
- 程序计数器是一块较小的内存区域，可以把它看成当前线程所执行的字节码的行号指示器。在虚拟机的概念模型里，字节码解释器工作时就是通过改变这个计数器的值来选取下一条需要执行的字节码指令，分支、循环、跳转、异常处理、线程恢复等都需要依赖这个计数器来完成。
- 程序计数器处于线程独占区。
- 如果线程执行的是Java方法，这个计数器记录的是正在执行的虚拟机字节码指令的地址。如果正在执行的是native方法，这个计数器的值则为空（undefined）。
- 此区域是唯一一个在Java虚拟机规范中没有规定任何 OutOfMemoryError 情况的区域。

#### Java 虚拟机栈（Java Virtual Machine Stacks）
- Java虚拟机栈描述的是Java方法执行的内存模型。
- Java 虚拟机栈处于线程独占区，它的生命周期与线程相同。
- 栈帧：每个方法在执行的同时都会创建一个栈帧（Stack Frame）  用于存储局部变量表，操作数栈，动态链接，方法出口等信息。每一个方法从调用直至执行完成的过程，就对应着一个栈帧在虚拟机栈中入栈到出栈的过程。
- 局部变量表：
    - 存放编译期可知道的各种基本数据类型，引用类型（reference 类型，它不等同于对象本身，可能是一个指向对象起始地址的引用指针，也可能是指向一个代表对象的句柄或其它与此对象相关的位置），returnAddress类型（指向了一条字节码指令的地址）。
    - 局部变量表所需的内存空间在编译期间完成分配，当进入一个方法时，这个方法需要在栈帧中分配多少内存是固定的，在方法运行期间是不会改变局部变量表的大小。
    - 其中 64 位长度的 long 和 double 类型的数据会占用 2 个局部变量空间（slot），其余数据只占用 1 个。 
- 异常
    - 线程请求的栈深度大于虚拟机所允许的深度，将抛出 StackOverflowError 异常。
    - 大部分 Java 虚拟机栈都允许动态扩展，如果扩展时无法申请到足够的内存，就会抛出 OutOfMemoryError 异常。

#### 本地方法栈（Native Method Stack）
- 本地方法栈与虚拟机栈的作用相似，不同在于虚拟机栈为虚拟机执行 Java 方法（字节码）服务，本地方法栈则为虚拟机执行 Native 方法服务。

#### Java 堆（Java Heap）
- Java 堆是 Java 虚拟机所管理的内存中最大的一块。
- Java 堆内存处于线程共享区，随着虚拟机的启动而创建。
- 所有的对象实例以及数组都要在堆上分配（随着JIT编译器的发展与逃逸分析技术逐渐成熟，栈上分配、标量替换优化技术将会导致一些对象不再堆上分配）。
- 垃圾收集器管理主要区域。
- Java 堆可以分为**新生代**和**老年代**，还可以细分为 Eden 区、From Survivor 区、To Survivor 区等。
- 当堆上没有内存可分配将抛出 OutOfMemoryError 异常。
 

#### 方法区（Method Area）
- 方法区又称永久代（Permanent Generation）。
- 用于存储被虚拟机加载的类信息（类版本、字段、方法和接口等）、常量、静态变量、即时编译器编译后的代码等数据。
- 方法区的内存回收目标主要是针对常量池的回收和对类型的卸载。
- 当方法区没有内存可分配将抛出 OutOfMemoryErro 异常。

#### 运行时常量池（Runtime Constant Pool）
- 运行时常量池是方法区的一部分。
- 存放类加载时类的常量和符号标识。Class 文件中除了存放类版本、字段、方法、接口等描述信息外，还有一项信息是常量池，用于存放编译期生成的各种字面量和符号引用，这部分内容将在类加载后进入方法区的常量池。
- Java 语言并不要求常量一定只有编译期才能产生，也就是并非预置入 Class 文件中常量池的内容才能进入方法区运行时常量池，运行期也可能将新的常量放入池中，常用的 `String` 类的 `intern()` 方法。
- 当常量池无法再申请到内存时会抛出 OutOfMemoryError 异常。

#### 直接内存（Direct Memory）
- 直接内存并不是虚拟机运行时数据区的一部分，因此直接内存的分配不会受到 Java 堆大小的限制。
- 通过使用 Native 函数库直接分配堆外内存，然后通过一个存储在 Java 堆中的 `DirectByteBuffer` 对象作为这块内存的引用进行操作。在一些场景中显著提高性能，因为避免了 Java 堆和 Native 堆来回复制数据。

### Java 对象

#### 对象的创建
 当虚拟机遇到一条 new 指令，首先去检查这条指令的参数是否能在常量池中定位到一个类的符号引用，并检查符号引用代表的类是否已被加载、解析和初始化过。如果没有，那必须先执行相应的类加载过程。在类加载检查通过后，虚拟机就为新生对象分配内存，对象所需内存的大小在类加载完成时就已确定，为对象分配空间的任务等同于把一块确定大小的内存从 Java 堆中划分出来。
 
##### 内存分配方式
 - 指针碰撞（Bump the Pointer）：如果 Java 堆中内存是绝对规整，所有用过的内存放在一边，空闲的内存放在另一边，中间放一个指针作为分界点的指示器，那分配内存操作只需将指针向空闲区移动所需大小。 
 - 空闲列表（Free List）：如果 Java 堆中的内存是不规整的，已使用的内存和空闲内存相互交错，那么虚拟机就必须维护一个列表，记录那些内存是可用的，在为对象分配内存时从列表中找到一块足够大的空间划分给对象实例并更新列表记录。
 
 选用那种分配方式由 Java 堆内存是否规整决定，而堆内存是否规整又由所采用的垃圾收集器是否带有压缩整理功能决定。

##### Java 虚拟机如何保证内存分配时的线程安全
 对像的创建在 Java 虚拟机中是非常频繁的行为，即使是仅仅修改一个指针所指的位置，在并发情况下也不是线程安全的。有以下两种方案解决这个问题。
 - 一种是对分配内存空间的动作进行同步处理（实际上虚拟机采用 CAS 配上失败重试的方式保证更新操作的原子性）。
 - 另一种是把内存分配的动作按照线程划分到不同的空间中进行，即每个线程在 Java 堆中预先分配一小块内存，称为本地线程分配缓冲（TLAB），那个线程要分配内存就在那个 TLAB 中分配，只有 TLAB 用完了分配新的 TLAB 后才需要同步锁定。虚拟机是否使用 TLAB 通过 -XX:+/-UseTLAB 参数来设定。

#### 对象的内存布局
对象在内存中存储的布局分为 3 块区域：
- 对象头（Header）：对象头包括两部分信息，第一部分用于存储对i轩昂自身的运行时数据，如哈希码、GC 分代年龄、锁状态标志、线程持有的锁等。另外一部分是类型指针，即对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是那个类的实例（并不是所有虚拟机的实现都必须在对象数据上保留类型指针）。如果对象是数组那在对象头中还必须有一块记录数组长度的数据，因为虚拟机可以通过普通 Java 对象的元数据信息确定 Java 对象的大小，但是从数组的元数据中却无法确定数组的大小。
- 实例数据（Instance Data）：对象中真正存储的有效信息，程序中所定义的各种类型的字段内容，无论是从父类继承下来的，还是在子类中定义的都需要记录下来。
- 对齐填充（Padding）：并不是必然存在的，仅仅起着占位符的作用。由于 HotSpot VM 的自动内存管理系统要求对象起始地址必须是 8 字节的整数 倍，当不是整数倍时就需要对齐填充来补齐。

####　对象的访问定位
