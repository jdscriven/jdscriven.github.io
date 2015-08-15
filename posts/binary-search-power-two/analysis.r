source("../../common/r/common.r")
background.color <- "#eee8d5"

hybrid.bs.data <-read.csv("java/hybridBinarySearch.csv", header=TRUE)

hybrid.bs.data.plot <- ggplot(data=hybrid.bs.data, aes(arraysize, time /128 * 4)) + 
           geom_point(alpha=1/2, size=1/3,color="orangered4") + coord_trans(x="log2") +
          scale_y_continuous(limits=c(0,3700),expand=c(0,300)) +
          xlab("Size of Array (bytes)") +
	  ylab("Clock cycles per lookup, ƒ=4.0GHz") +
 	  ggtitle("Warm Cache Binary Search Lookup Times") +
          theme(text = element_text(size=14)) +
          theme(axis.text.x = element_text(size=8)) +
          theme(panel.background = element_rect(fill =background.color)) +
          cpu.x + cpu.text +  cpu.regions

hybrid.bs.data.mean <- setNames(aggregate(hybrid.bs.data[,2],list(hybrid.bs.data$arraysize), mean), c("arraysize","time"))

hybrid.bs.data.mean.plot <- ggplot(hybrid.bs.data.mean, aes(arraysize, time/128 * 4)) +
          geom_point(alpha=1/1, size=1.5,color="orangered4") + coord_trans(x="log2") +
          scale_y_continuous(limits=c(0,3700),expand=c(0,300)) +
          xlab("Size of Array (bytes)") +
	  ylab("Clock cycles per lookup, ƒ=4.0GHz") +
 	  ggtitle("Warm Cache Binary Search Lookup Times\n(mean of each measured SIZE)") +
          theme(text = element_text(size=14)) +
          theme(axis.text.x = element_text(size=8)) +
          theme(panel.background = element_rect(fill =background.color)) +
          cpu.x + cpu.text +  cpu.regions

ggsave(hybrid.bs.data.plot,width=9, height=6, dpi=300, filename="images/hybrid-binary-search-power-two.jpg")
ggsave(hybrid.bs.data.mean.plot,width=9, height=6, dpi=300, filename="images/hybrid-binary-search-power-two-mean.jpg")

bs.data <-read.csv("java/binarySearch.csv", header=TRUE)

bs.data.plot <- ggplot(data=bs.data, aes(arraysize, time /128 * 4)) + 
           geom_point(alpha=1/2, size=1/3,color="orangered4") + coord_trans(x="log2") +
          scale_y_continuous(limits=c(0,3700),expand=c(0,300)) +
          xlab("Size of Array (bytes)") +
	  ylab("Clock cycles per lookup, ƒ=4.0GHz") +
 	  ggtitle("Warm Cache Binary Search Lookup Times") +
          theme(text = element_text(size=14)) +
          theme(axis.text.x = element_text(size=8)) +
          theme(panel.background = element_rect(fill =background.color)) +
          cpu.x + cpu.text +  cpu.regions

bs.data.mean <- setNames(aggregate(bs.data[,2],list(bs.data$arraysize), mean), c("arraysize","time"))

bs.data.mean.plot <- ggplot(bs.data.mean, aes(arraysize, time/128 * 4)) +
          geom_point(alpha=1/1, size=1.5,color="orangered4") + coord_trans(x="log2") +
          scale_y_continuous(limits=c(0,3700),expand=c(0,300)) +
          xlab("Size of Array (bytes)") +
	  ylab("Clock cycles per lookup, ƒ=4.0GHz") +
 	  ggtitle("Warm Cache Binary Search Lookup Times\n(mean of each measured SIZE)") +
          theme(text = element_text(size=14)) +
          theme(axis.text.x = element_text(size=8)) +
          theme(panel.background = element_rect(fill =background.color)) +
          cpu.x + cpu.text +  cpu.regions

ggsave(bs.data.plot,width=9, height=6, dpi=300, filename="images/binary-search-power-two.jpg")
ggsave(bs.data.mean.plot,width=9, height=6, dpi=300, filename="images/binary-search-power-two-mean.jpg")


fixed.bs.data <-read.csv("java/fixedBinarySearch.csv", header=TRUE)

fixed.bs.data.plot <- ggplot(data=fixed.bs.data, aes(arraysize, time /128 * 4)) + 
           geom_point(alpha=1/2, size=1/3,color="orangered4") + coord_trans(x="log2") +
          scale_y_continuous(limits=c(0,3700),expand=c(0,300)) +
          xlab("Size of Array (bytes)") +
	  ylab("Clock cycles per lookup, ƒ=4.0GHz") +
 	  ggtitle("Warm Cache Binary Search Lookup Times") +
          theme(text = element_text(size=14)) +
          theme(axis.text.x = element_text(size=8)) +
          theme(panel.background = element_rect(fill =background.color)) +
          cpu.x + cpu.text +  cpu.regions

fixed.bs.data.mean <- setNames(aggregate(fixed.bs.data[,2],list(fixed.bs.data$arraysize), mean), c("arraysize","time"))

fixed.bs.data.mean.plot <- ggplot(fixed.bs.data.mean, aes(arraysize, time/128 * 4)) +
          geom_point(alpha=1/1, size=1.5,color="orangered4") + coord_trans(x="log2") +
          scale_y_continuous(limits=c(0,3700),expand=c(0,300)) +
          xlab("Size of Array (bytes)") +
	  ylab("Clock cycles per lookup, ƒ=4.0GHz") +
 	  ggtitle("Warm Cache Binary Search Lookup Times\n(mean of each measured SIZE)") +
          theme(text = element_text(size=14)) +
          theme(axis.text.x = element_text(size=8)) +
          theme(panel.background = element_rect(fill =background.color)) +
          cpu.x + cpu.text +  cpu.regions

ggsave(fixed.bs.data.plot,width=9, height=6, dpi=300, filename="images/fixed-binary-search-power-two.jpg")
ggsave(fixed.bs.data.mean.plot,width=9, height=6, dpi=300, filename="images/fixed-binary-search-power-two-mean.jpg")


