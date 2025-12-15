package sys.figure.inter;

public interface Figure {
    default float[] getSizes() {
        return null;
    }

    default float perimeter() {
        float[] sizes = getSizes();
        if (sizes == null || sizes.length > 3) {
            return 0;
        }
        switch (sizes.length) {
            case 3:
                return sizes[0] + sizes[1] + sizes[2];
            case 2:
                return (sizes[0] + sizes[1]) * 2;
            case 1:
                return 2 * (float) Math.PI * sizes[0];
        }
        return 0;
    }

    default float area() {
        float[] sizes = getSizes();
        if (sizes == null || sizes.length > 3) {
            return 0;
        }
        switch (sizes.length) {
            case 3:
                if (sizes[0] + sizes[1] <= sizes[2] || sizes[0] + sizes[2] <= sizes[1] || sizes[1] + sizes[2] <= sizes[0] ||
                        sizes[0] <= 0 || sizes[1] <= 0 || sizes[2] <= 0) {
                    return 0;
                }
                float s = (sizes[0] + sizes[1] + sizes[2]) / 2.0f;
                return (float) Math.sqrt(s * (s - sizes[0]) * (s - sizes[1]) * (s - sizes[2]));
            case 2:
                return sizes[0] * sizes[1];
            case 1:
                return (float) Math.pow(Math.PI * sizes[0], 2);
        }
        return 0;
    }
}
