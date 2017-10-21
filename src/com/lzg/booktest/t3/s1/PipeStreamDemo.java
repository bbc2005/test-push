package com.lzg.booktest.t3.s1;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;

/** 
 * ͨ���ܵ������̼߳�ͨ��
 * ��Java�������ṩ�˸��ָ���������/�����Stream��
 * �ܹ��ܷ���ض����ݽ��в��������йܵ���(pipeStream)��һ��
 * ��������������ڲ�ͬ�̼߳�ֱ�Ӵ������ݡ�һ���̷߳������ݵ�����ܵ�
 * ����һ���̴߳�����ܵ��ж����ݡ�ͨ��ʹ�ùܵ���ʵ�ֲ�ͬ�̼߳��ͨ�ţ�
 * �����������������ʱ�ļ�֮��Ķ�����
 * @author lzg 
 * @desc
 * @version 0.0.1
 * @time  ����ʱ�䣺2017��8��24�� ����3:21:27 
 */
public class PipeStreamDemo {
	/**
	 * ͨ���ܵ������̼߳�ͨ�ţ��ֽ���
	 * @param args
	 * 2017��8��24�� ����3:26:44
	 */
	public static void main1(String[] args) {
		try {
			PipeInputOutputWriteData writeData = new PipeInputOutputWriteData();
			PipeInputOutputReadData readData = new PipeInputOutputReadData();
			PipedInputStream inputStream = new PipedInputStream();
			PipedOutputStream outputStream = new PipedOutputStream();
			//inputStream.connect(outputStream);
			outputStream.connect(inputStream);//���ã�ʹ����Stream֮�����ͨ�����ӣ��ſ��Խ�������������롣
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
	 * ͨ���ܵ������̼߳�ͨ�ţ��ַ���
	 * @param args
	 * 2017��8��24�� ����3:59:45
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
			System.out.println("read  ��");
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
				String outData = "�й���ʦ��" + (i + 1);
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