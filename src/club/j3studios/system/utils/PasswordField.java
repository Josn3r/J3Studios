package club.j3studios.system.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicPasswordFieldUI;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import club.j3studios.system.utils.shadow.ShadowRenderer;

public class PasswordField extends JPasswordField {

	private static final long serialVersionUID = 1L;

	public boolean isShowAndHide() {
        return showAndHide;
    }

    public void setShowAndHide(boolean showAndHide) {
        this.showAndHide = showAndHide;
        repaint();
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }
    
	public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
        createImageShadow();
        repaint();
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        createImageShadow();
        repaint();
    }
    
    private final Animator animator;
    private float location;
    private String labelText = "Label";
    private final Image eye;
    private final Image eye_hide;
    private boolean hide = true;
    private boolean showAndHide;

    private int round = 10;
    private Color shadowColor = new Color(170, 170, 170);
    private BufferedImage imageShadow;
    private final Insets shadowSize = new Insets(2, 5, 8, 5);

    public PasswordField() {
        setUI(new TextUI());
        setOpaque(false);
        setForeground(new Color(80, 80, 80));
        setSelectedTextColor(new Color(255, 255, 255));
        setSelectionColor(new Color(133, 209, 255));
        setBorder(new EmptyBorder(10, 12, 15, 12));
        setBackground(new Color(255, 255, 255));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                if (showAndHide) {
                    int x = getWidth() - 30;
                    if (new Rectangle(x, 0, 30, 30).contains(me.getPoint())) {
                        hide = !hide;
                        if (hide) {
                            setEchoChar('*');
                        } else {
                            setEchoChar((char) 0);
                        }
                        repaint();
                    }
                }
            }
        });
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent fe) {
                showing();
            }

            @Override
            public void focusLost(FocusEvent fe) {
                showing();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                if (showAndHide) {
                    int x = getWidth() - 30;
                    if (new Rectangle(x, 0, 30, 30).contains(me.getPoint())) {
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                    } else {
                        setCursor(new Cursor(Cursor.TEXT_CURSOR));
                    }
                }
            }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                String.valueOf(getPassword()).equals("");
            }

            @Override
            public void timingEvent(float fraction) {
                location = fraction;
                repaint();
            }

        };
        eye = new ImageIcon(getClass().getResource("/images/eye.png")).getImage();
        eye_hide = new ImageIcon(getClass().getResource("/images/eye_hide.png")).getImage();
        
        animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
    }

    private void showing() {
        if (animator.isRunning()) {
            animator.stop();
        } else {
            location = 1;
        }
        animator.setStartFraction(1f - location);
        location = 1f - location;
        animator.start();
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        double width = getWidth() - (shadowSize.left + shadowSize.right);
        double height = getHeight() - (shadowSize.top + shadowSize.bottom);
        double x = shadowSize.left;
        double y = shadowSize.top;
        //  Create Shadow Image
        g2.drawImage(imageShadow, 0, 0, null);
        //  Create Background Color
        g2.setColor(getBackground());
        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, round, round));
        g2.fill(area);
        if (showAndHide) {
            createShowHide(g2);
        }
        g2.dispose();
        super.paintComponent(grphcs);
    }
    
    private void createShowHide(Graphics2D g2) {
        int x = getWidth() - 40 + 5;
        int y = (getHeight() - 25) / 2;
        g2.drawImage(hide ? eye_hide : eye, x, y, null);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        createImageShadow();
    }

    private void createImageShadow() {
        int height = getHeight();
        int width = getWidth();
        if (width > 0 && height > 0) {
            imageShadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = imageShadow.createGraphics();
            BufferedImage img = createShadow();
            if (img != null) {
                g2.drawImage(createShadow(), 0, 0, null);
            }
            g2.dispose();
        }
    }

    private BufferedImage createShadow() {
        int width = getWidth() - (shadowSize.left + shadowSize.right);
        int height = getHeight() - (shadowSize.top + shadowSize.bottom);
        if (width > 0 && height > 0) {
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fill(new RoundRectangle2D.Double(0, 0, width, height, round, round));
            g2.dispose();
            return new ShadowRenderer(5, 0.3f, shadowColor).createShadow(img);
        } else {
            return null;
        }
    }

    private class TextUI extends BasicPasswordFieldUI {
        @Override
        protected void paintBackground(Graphics grphcs) {
            
        }
    }
}