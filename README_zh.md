# PageRanker

![LOGO](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/blob/master/resource/logo/PageRanker.png)

## 索引

[TOC]

## 简介

>PageRanker 是一个开源的、基于 [Hadoop](http://hadoop.apache.org) MapReduce 的 PageRank 算法实现项目。该项目可以处理海量页面的排序，并以特殊格式给出。这种算法可以应用到很多方面，如搜索引擎对网页的排序、评估时事热点发现网络节点之间的关系等。此项目用 Java 语言编写，数据集利用 [Epinions 社交数据集](https://snap.stanford.edu/data/soc-Epinions1.html)，参考了拉⾥佩琦先⽣生著名的 [*The PageRank Citation Ranking︎ Bringing Order to the Web*](http://202.116.81.74/cache/5/03/ilpubs.stanford.edu/bf0bd3cdc413c81dc4853ddffe4de51f/1999-66.pdf)论⽂。PageRank 算法的 Matlab 实现可以参考我的另一个 GitHub 仓库[Matlab_NumericalComputing_Experiments](https://github.com/LovelyBuggies/Matlab_NumericalComputing_Experiments)的 PageRank 部分。

## 功能

1. 优质的网页排序功能
2. 海量数据的处理
3. 关系型数据库系统的结果处理（挖个坑，未来实现）

## 目录
</br>
### src 
src 是项目的源代码部分，提供了 NodeConverter 和 PageRank 的 Java 实现。

* NodeConverter
提供了 NodeConverter 部分的 MapReduce 实现，把数据集节点指向关系的格式转换。

* PageRank
提供了 PageRank 部分的 MapReduce 实现，对网络节点的 PageRank 值进行了排序。


### bin
bin 是项目的可运行文件部分，提供了 NodeConverter 和 PageRank 的 Java 的可运行文件。

* NodeConverter
提供了 NodeConverter 部分的 MapReduce 可运行文件并打包，把数据集节点指向关系的格式转换。

* PageRank
提供了 PageRank 部分的 MapReduce 可运行文件并打包，对网络节点的 PageRank 值进行了排序。


### resource
提供了项目可用的资源文件。

* dataset
提供了项目用到的数据集。

* downloads
提供了项目用到的 jdk 压缩包、Hadoop 压缩包、Xshell 运行文件。

* labs
提供了项目参考的 Easyhpc 网站的实验。

* logo
项目 LOGO。

* paper
项目参考的论文，为算法提供理论支撑。

* vm
已经配置好的环境，方便之后的运行工作。

* report
项目的实验报告，课程设计。


### README-zh.md
中文版 README。


### README.md
英文版 README。

</br>

## 用法

1.您可以在 /resource/dataset/ 中找到实验用到的数据集，拷贝 /src/* 下的相关代码在您已经配置好的 Hadoop 和 Java 的云平台上进行测试。

2.您也可以在 /resource/vm/* 下找到笔者已经配置好的虚拟机节点进行测试。把虚拟机复制到您的 VM Workstation 12 pro 的目录下，打开每个节点的 *.vmx 文件，此时应该可以正常运行云实例。每个节点的用户名和密码分别是 Hadoop 和 1998，进行初始化操作 source setenv.sh 和 start-hdfs、start—yarn 操作（详见实验报告的实验步骤）。因为代码 *.java 和运行文件 *.class 和 .jar 文件已经被笔者利用 lrszs 文件传输助手传至 master 云实例下，所以无需再次传输。此后，您就可以 MapReduce NodeConverter.jar 和 PageRank.jar。

**该项目部署配置环境比较复杂，所以笔者提供了虚拟机云实例的拷贝。**

## 版权

代码内容采用 [GPL 许可](http://www.gnu.org/licenses/gpl.html)
代码内容采用 [Mozilla 许可](https://www.mozilla.org/en-US/MPL/)

Copyright © Nino 2018.


## 特别鸣谢

* 感谢 [Zicong Hong](https://github.com/hongzicong) 给笔者提供了在 Hadoop 分布式编程框架上编写 PageRank 的思路参考。
* 感谢 Weig Wu 半年来提供的技术辅导。
* Sherry Fan 对我代码工作和学习生活的一贯支持。


