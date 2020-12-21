package net.thumbtack.school.file;

import com.google.gson.Gson;
import net.thumbtack.school.colors.v3.ColorException;
import net.thumbtack.school.figures.v3.ColoredRectangle;
import net.thumbtack.school.figures.v3.Rectangle;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;
import java.io.*;
import java.util.Scanner;

abstract class FileService {

    public static void writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(array);
        }
    }

    public static void writeByteArrayToBinaryFile(File file, byte[] array) throws IOException {
        writeByteArrayToBinaryFile(file.getPath(), array);
    }

    public static byte[]  readByteArrayFromBinaryFile(String fileName) throws IOException {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            return fis.readAllBytes();
        }
    }

    public static byte[]  readByteArrayFromBinaryFile(File file)  throws IOException {
        return readByteArrayFromBinaryFile(file.getPath());
    }

    public static byte[]  writeAndReadByteArrayUsingByteStream( byte[] array) throws IOException {
        byte[] evenBytes = new byte[array.length / 2];
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            baos.write(array);
            try (ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray())) {
                for (int i = 0; i < evenBytes.length; i++) {
                    evenBytes[i] = (byte) bais.read();
                    bais.skip(1);
                }
            }
            return evenBytes;
        }
    }

    public static void  writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName))) {
            bos.write(array);
        }
    }

    public static void  writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        writeByteArrayToBinaryFileBuffered(file.getPath(), array);
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {
            return bis.readAllBytes();
        }
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        return readByteArrayFromBinaryFileBuffered(file.getPath());
    }

    public static void  writeRectangleToBinaryFile(File file, Rectangle rect) throws IOException {
       try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file, true))) {
           dos.writeInt(rect.getTopLeft().getX());
           dos.writeInt(rect.getTopLeft().getY());
           dos.writeInt(rect.getBottomRight().getX());
           dos.writeInt(rect.getBottomRight().getY());
       }
    }

    public static Rectangle  readRectangleFromBinaryFile(File file) throws IOException {
       try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
           return new Rectangle(dis.readInt(), dis.readInt(), dis.readInt(), dis.readInt());
       }
    }

    public static void  writeColoredRectangleToBinaryFile(File file, ColoredRectangle rect) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(rect.getTopLeft().getX());
            dos.writeInt(rect.getTopLeft().getY());
            dos.writeInt(rect.getBottomRight().getX());
            dos.writeInt(rect.getBottomRight().getY());
            dos.writeUTF(rect.getColor().toString());
        }
    }

    public static ColoredRectangle  readColoredRectangleFromBinaryFile(File file) throws IOException, ColorException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            return new ColoredRectangle(dis.readInt(), dis.readInt(), dis.readInt(), dis.readInt(), dis.readUTF());
        }
    }

    public static void  writeRectangleArrayToBinaryFile(File file, Rectangle[] rects ) throws IOException {
        for (Rectangle rect : rects) {
            writeRectangleToBinaryFile(file, rect);
        }
    }

    public static Rectangle[]  readRectangleArrayFromBinaryFileReverse(File file) throws IOException {
        Rectangle[] rectangles = new Rectangle[(int) (file.length() / 16)];
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            for (int i = rectangles.length - 1; i >= 0; i--) {
                rectangles[i] = new Rectangle(dis.readInt(), dis.readInt(), dis.readInt(), dis.readInt());
            }
            return rectangles;
        }
    }

    public static void  writeRectangleToTextFileOneLine(File file, Rectangle rect) throws IOException {
        writeRectangleToBinaryFile(file, rect);
    }

    public static Rectangle  readRectangleFromTextFileOneLine(File file) throws IOException {
        return readRectangleFromBinaryFile(file);
    }

    public static void  writeRectangleToTextFileFourLines(File file, Rectangle rect) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            writer.write(rect.getTopLeft().getX() + "\n");
            writer.write(rect.getTopLeft().getY() + "\n");
            writer.write(rect.getBottomRight().getX() + "\n");
            writer.write(rect.getBottomRight().getY() + "\n");
        }
    }

    public static Rectangle  readRectangleFromTextFileFourLines(File file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            return new Rectangle(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }
    }

    public static void  writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeUTF(trainee.getFirstName());
            dos.writeUTF(" ");
            dos.writeUTF(trainee.getLastName());
            dos.writeUTF(" ");
            dos.writeUTF(String.valueOf(trainee.getRating()));
            dos.writeUTF(" ");
        }
    }

    public static Trainee  readTraineeFromTextFileOneLine(File file) throws IOException, TrainingException {
        String[] strings = new String[3];
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            for (int i = 0; i < strings.length; i++) {
                strings[i] = dis.readUTF();
                dis.skip(3);
            }
        }
        return new Trainee(strings[0], strings[1], Integer.parseInt(strings[2]));
    }

    public static void  writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeUTF(trainee.getFirstName());
            dos.writeUTF("\n");
            dos.writeUTF(trainee.getLastName());
            dos.writeUTF("\n");
            dos.writeUTF(String.valueOf(trainee.getRating()));
            dos.writeUTF("\n");
        }
    }

    public static Trainee  readTraineeFromTextFileThreeLines(File file) throws IOException, TrainingException {
        return readTraineeFromTextFileOneLine(file);
    }

    public static void  serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(trainee);
        }
    }

    public static Trainee  deserializeTraineeFromBinaryFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Trainee) ois.readObject();
        }
    }

    public static String  serializeTraineeToJsonString(Trainee trainee) {
        return new Gson().toJson(trainee);
    }

    public static Trainee  deserializeTraineeFromJsonString(String json) {
        return new Gson().fromJson(json, Trainee.class);
    }

    public static void  serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            new Gson().toJson(trainee, bw);
        }
    }

    public static Trainee  deserializeTraineeFromJsonFile(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return new Gson().fromJson(br, Trainee.class);
        }
    }
}









