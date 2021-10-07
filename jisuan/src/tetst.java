import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class tetst {

    public static void main(String[] args) {
        // 创建窗体对象
        Frame f = new Frame();
        // Frame ff = new Frame("ahahahah") 构造给窗体标题

        // 设置窗体标题
        f.setTitle("hahahaha");

        // 调节窗口大小
        f.setSize(500, 500);
        // 设置窗口位置
        // f.setLocation(400,200);

        // f.setBounds(x, y, width, height);
        // 设置窗口居中
        f.setLocationRelativeTo(null);

        // 流式布局
        f.setLayout(new FlowLayout());

        // 创建文本框
        TextField tf = new TextField(20); // 可以存20个字符

        // 创建按钮
        Button bu = new Button("数据转移");

        // 创建文本域
        TextArea ta = new TextArea(10, 40);

        // 组件添加到窗体
        f.add(tf);
        f.add(bu);
        f.add(ta);

        // 对按钮添加事件
        bu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // 获取文本框的值
                String tf_str = tf.getText().trim();
                // 清空数据
                tf.setText("");

                // 设置给文本域
                // ta.setText(tf_str);

                // 字符串比较
                if (tf_str.equals("123")) {
                    // 追加和换行
                    ta.append("123" + "\r\n");
                } else
                    ta.append("输错了" + "\r\n");

                // 获取光标
                tf.requestFocus();
            }
        });

        // 适配器改进窗口关闭
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });
        // 窗体可见
        f.setVisible(true);
    }

}
