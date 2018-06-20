# Java_CloudComputing_PageRanker

README: [English](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/blob/master/README.md) | [中文](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/blob/master/README_zh.md)

![LOGO](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/blob/master/resource/logo/PageRanker.png)

## Index

[TOC]

## Introductino

>PageRanker is an open source implementation of Page Rank Algorithm by Larry Page based on [Hadoop](http://hadoop.apache.org) MapReduce. Showing splendid performance while dealing with huge amounts of data, the project given results of PageRank values for numerous net nodes. This algorithm can be applied to many aspects, such as ranking of web pages for search engines, evaluation of hot spots, finding network nodes relation and so on. I used Java to compete this onerous task and [Epinions Social Dataset](https://snap.stanford.edu/data/soc-Epinions1.html) was included as my test dataset. The theoretical support referred to the famous article [*The PageRank Citation Ranking︎ Bringing Order to the Web*](http://202.116.81.74/cache/5/03/ilpubs.stanford.edu/bf0bd3cdc413c81dc4853ddffe4de51f/1999-66.pdf) by Larry Page. Matlab implementation of this algorithm can be referred to the *PageRank* part of another GitHub repository of mine[Matlab_NumericalComputing_Experiments](https://github.com/LovelyBuggies/Matlab_NumericalComputing_Experiments).

## Features

1. Excellent web page ranking function.
2. Processing of massive data.
3. Results SQL DataBase enablility. (plan to complete in the future)

## Contains
</br>

### src 
**src - source codes of the project, providing the Java implementation of NodeConverter and PageRank.**

* [NodeConverter](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/src/NodeConverter)
The MapReduce implementation of the NodeConverter, providing conversion of data format.

* [PageRank](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/src/PageRank)
The MapReduce implementation of the PageRank, ranking the network nodes.


### bin
**bin - runner of the project, providing the runner of NodeConverter and PageRank.**

* [NodeConverter](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/bin/NodeConverter)
The MapReduce runner of the NodeConverter, providing conversion of data format.

* [PageRank](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/bin/PageRank)
The MapReduce runner of the PageRank, ranking the network nodes.

### report
[云计算.pdf](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/report)：**Study Report, giving procedure and result.**

### resource
**Resource available.**

* [dataset](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/resource/dataset)
Dataset used in the project are provided.

* [downloads](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/resource/downloads)
JDK compression packages, Hadoop compression packages, and Xshell runner used in the project are provided.

* [labs](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/resource/labs)
The [Easyhpc](http://www.easyhpc.org) site experiment, which provides a reference to the project.

* [logo](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/resource/logo)
Project [LOGO](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/blob/master/resource/logo/PageRanker.png).

* [paper](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/resource/paper)
[*The PageRank Citation Ranking︎ Bringing Order to the Web*](http://202.116.81.74/cache/5/03/ilpubs.stanford.edu/bf0bd3cdc413c81dc4853ddffe4de51f/1999-66.pdf), providing theoretical supports.  

* [vm](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/resource/vm)
Virtual Machines - master, slave1, slave2. 


### README-zh.md
**Chinese README。**


### README.md
**English README。**

</br>

## Usage

1. You can get dataset of this experiment in floader [/resource/dataset/](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/resource/dataset), download codes from [/src/](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/src), and run the project on your own instances configured.

2. Virtual Machines are available under the floader [/resource/vm/*](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/resource/vm). These are instances I configured, run instances on VM Workstation 12 pro. For each nodes, the username and password are initialized as 'Hadoop' and '1998' respectively. Source setenv.sh, then start hdfs and yarn (details in [study report](https://github.com/LovelyBuggies/Java_Hadoop_PageRanker/tree/master/report)). Then you can map and reduce the project and get the ideal answers.

**Considering the complexity of environment configuration, I upload the VM instances by design.**

## Copyright

[GPL lisence](http://www.gnu.org/licenses/gpl.html)

[Mozilla lisence](https://www.mozilla.org/en-US/MPL/)

Copyright © Nino 2018.


## Thanks

* [Zicong Hong](https://github.com/hongzicong) awake me in the beginning of this project.
* My mentor Weig Wu's technical assistance.
* Sherry Fan, my lover, has always supported my code work and study life behind my back.


