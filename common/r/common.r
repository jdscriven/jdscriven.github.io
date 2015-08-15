format_si <- function(...) {
  function(x) {
    limits <- c( 1, 2^10,2^20,2^30,2^40,2^50,2^60 )
    prefix <- c( "", "k", "M", "G", "T", "P", "E"  )
 
    # Vector with array indices according to position in intervals
    i <- findInterval(abs(x), limits)
 
    # Set prefix to " " for very small values < 1e-24
    #i <- ifelse(i==0, which(limits == 1e0), i)

    paste(format(round(x/limits[i], 1),
                 trim=TRUE, scientific=FALSE, ...),
          prefix[i], sep="")
  }
}
library(gridExtra)
library(ggplot2)
library(lattice)
library(grid)
library(scales)


cpu.x <- scale_x_continuous(breaks = 2^seq(2,33,by=1) ,labels=format_si(), minor_breaks=2^seq(2,33,by=1)+2^seq(1,32,by=1), limits=c(4096,2^32), expand=c(0.01,0))
cpu.y <- scale_y_continuous(limits=c(0,900),expand=c(0,85)) 
cpu.text <- geom_vline(xintercept= c(32*1024, 256*1024, 8*1024*1024), color = "grey25", alpha=0.3) 
cpu.regions <- annotate("text", x = c(1024*12, 1024*96, 1024*1400, 1024*1024*196),vjust=-1, y=-Inf,label = c("L1 (32k)", "L2 (256k)", "L3 (8M)", "Main Memory"), colour = "grey25", size = 4) 

happycachedata <- read.csv("~/happycacheresults-power2.csv", header=TRUE)
plot1 <- ggplot(data=happycachedata, aes(arraysize, time/10000, col=algorithm)) +
 geom_vline(xintercept= c(32*1024, 256*1024, 8*1024*1024), color = "grey25", alpha=0.3) +
 geom_line() + geom_point() +
 scale_y_continuous(expand=c(0,100), breaks=seq(0,1000,by=100)) +
 scale_x_continuous(limits=c(1024,1024*1024*1024*6), trans=log2_trans(), breaks = 2^seq(2,32,by=2) ,labels=format_si())  +
 ylab("Average time per search (ns)") +
 xlab("Array Size (bytes)") +
 theme_bw()+
 geom_hline(x=0, color="grey50") +
 theme(legend.position = "none", panel.margin=unit(c(3,3,3,3),units="line")) +
 annotate("text", x = c(1024*4, 1024*96, 1024*1400, 1024*1024*256),vjust=-1, y=-Inf,label = c("L1 Cache (32k)", "L2 Cache (256k)", "L3 Cache (8M)", "Main Memory"), colour = "grey70", size = 4) +
 geom_text(data = happycachedata[happycachedata$arraysize == 1024*1024*1024*2, ], aes(label = algorithm),  vjust=2, hjust=0 )


happycachedata <- read.csv("~/happycacheresults-linear.csv", header=TRUE)
#foobar <- aggregate(happycachedata["time"], by=happycachedata[c("algorithm")],FUN=mean)

plot2 <- ggplot(data=happycachedata, aes(arraysize, time/10000, col=algorithm)) +
 geom_point(size=1, position = position_jitter(w=0.01, h=0))  +
 scale_y_continuous(limits=c(0,1000), expand=c(0,100), breaks=seq(0,2000,by=200)) +
 scale_x_continuous(limits=c(1024,1024*1024*1024*6), trans=log2_trans(), breaks = 2^seq(2,32,by=2) ,labels=format_si())  +
 ylab("Average time per search (ns)") +
 xlab("Array Size (bytes)") +
 theme_bw()+
 geom_hline(x=0, color="grey50") +
 theme(legend.position = "none", panel.margin=unit(c(3,3,3,3),units="line")) 

#binarysearchaliasing <-read.csv("~/binarysearchaliasing.csv", header=TRUE)
#
#foobar1 <- ggplot(data=binarysearchaliasing, aes(arraysize, time /256)) + 
#           geom_point(alpha=1/2, size=1/3) + coord_trans(x="log2") +
#           cpu.x + cpu.y + cpu.text +  cpu.regions
#
#d <- setNames(aggregate(binarysearchaliasing[,3],list(binarysearchaliasing$arraysize), mean), c("arraysize","time"))
#
#foobar <- ggplot(d, aes(arraysize, time/256)) +
#          geom_point(alpha=1/1, size=1.5) + coord_trans(x="log2") +
#          cpu.x + cpu.y + cpu.text +  cpu.regions
#
#

#guides(colour = guide_legend(override.aes = list(size=5, alpha=1)))
