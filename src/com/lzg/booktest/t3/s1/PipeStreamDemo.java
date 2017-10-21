package com.lzg.booktest.t3.s1;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;

/** 
 * 通过管道进行线程间通信
 * 在Java语言中提供了各种各样的输入/输出流Stream，
 * 能够很方便地对数据进行操作，其中管道流(pipeStream)是一种
 * 特殊的流，用于在不同线程间直接传送数据。一个线程发送数据到输出管道
 * ，另一个线程从输入管道中读数据。通过使用管道，实现不同线程间的通信，
 * 而无须借助于类似临时文件之类的东西。
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  创建时间：2017年8月24日 下午3:21:27 
 */
public class PipeStreamDemo {
	/**
	 * 通过管道进行线程间通信：字节流
	 * @param args
	 * 2017年8月24日 下午3:26:44
	 */
	public static void main1(String[] args) {
		try {
			PipeInputOutputWriteData writeData = new PipeInputOutputWriteData();
			PipeInputOutputReadData readData = new PipeInputOutputReadData();
			PipedInputStream inputStream = new PipedInputStream();
			PipedOutputStream outputStream = new PipedOutputStream();
			//inputStream.connect(outputStream);
			outputStream.connect(inputStream);//作用：使两个Stream之间产生通信连接，才可以将数据输出与输入。
			PsdThreadRead readThread = new PsdThreadRead(readData, inputStream);
			readThread.start();
			Thread.sleep(2000);
			PsdThreadWrite writeThread = new PsdThreadWrite(writeData,
					outputStream);
			writeThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 通过管道进行线程间通信：字符流
	 * @param args
	 * 2017年8月24日 下午3:59:45
	 */
	public static void main(String[] args) {
		try {
			PipeReaderWriterWriteData writeData = new PipeReaderWriterWriteData();
			PipeReaderWriterReadData readData = new PipeReaderWriterReadData();
			PipedReader input = new PipedReader();
			PipedWriter out = new PipedWriter();
			//		input.connect(out);
			out.connect(input);
			ThreadRead readThread = new ThreadRead(readData, input);
			readThread.start();
			Thread.sleep(2000);
			ThreadWrite writeThread = new ThreadWrite(writeData, out);
			writeThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
class PipeInputOutputWriteData{
	public void write(PipedOutputStream out){
		try {
			System.out.println("write:");
			for (int i = 0; i < 300; i++) {
				String outData = "" + (i + 1);
				out.write(outData.getBytes());
				System.out.print(outData + ",");
			}
			System.out.println();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class PipeInputOutputReadData{
	public void read(PipedInputStream input){
		try {
			System.out.println("read  ：");
			byte[] byteArray = new byte[20];
			int readLength = input.read(byteArray);
			while (readLength != -1) {
				String newData = new String(byteArray, 0, readLength);
				System.out.print(newData);
				readLength = input.read(byteArray);
			}
			System.out.println();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class PsdThreadWrite extends Thread{
	private PipeInputOutputWriteData write;
	private PipedOutputStream out;
	public PsdThreadWrite(PipeInputOutputWriteData write, PipedOutputStream out) {
		super();
		this.write = write;
		this.out = out;
	}
	@Override
	public void run() {
		write.write(out);
	}
	
}
class PsdThreadRead extends Thread{
	private PipeInputOutputReadData read;
	private PipedInputStream input;
	public PsdThreadRead(PipeInputOutputReadData read, PipedInputStream input) {
		super();
		this.read = read;
		this.input = input;
	}
	@Override
	public void run() {
		read.read(input);
	}
	
}
class PipeReaderWriterWriteData{
	public void write(PipedWriter out){
		try {
			System.out.println("wirte :");
			for (int i = 0; i < 300; i++) {
//				String outData = "" + (i + 1);
				String outData = "中国大师傅" + (i + 1);
				out.write(outData);
				System.out.print(outData);
			}
			System.out.println();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class PipeReaderWriterReadData{
	public void read(PipedReader input){
		try {
			System.out.println("read :");
			char[] byteArray = new char[20];
			int readLength = input.read(byteArray);
			while (readLength != -1) {
				String newData = new String(byteArray, 0, readLength);
				System.out.print("-->" + newData);
				readLength = input.read(byteArray);
			}
			System.out.println();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ThreadWrite extends Thread{
	private PipeReaderWriterWriteData write;
	private PipedWriter out;
	public ThreadWrite(PipeReaderWriterWriteData write, PipedWriter out) {
		super();
		this.write = write;
		this.out = out;
	}
	@Override
	public void run() {
		write.write(out);
	}
}
class ThreadRead extends Thread{
	private PipeReaderWriterReadData read;
	private PipedReader input;
	public ThreadRead(PipeReaderWriterReadData read, PipedReader input) {
		super();
		this.read = read;
		this.input = input;
	}
	@Override
	public void run() {
		read.read(input);
	}
}