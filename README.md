# Graphical illustration of a fixed point iteration 

## External JARs
For this program to work, following libraries must be included:
* _exp4j_ to evaluate function value 
* _jfreechart_ to draw a chart

Both JARs are included.

## What is a fixed point?
In mathematics, a fixed point of a function is an element, that is mapped on itself by the function; thus $f(x)=x$ for some $x \in D(f)$. This can be illustrated by an iteration starting in a starting point $x_0$ and ending in a fixed point $x $. If the fixed point does not exist or the starting point is not chosen suitably, then the fixed point is not found.
## Inputs
To illustrate fixed point iteration, these inputs must be defined:
* function $f(x)$

* starting point $x_0$

* precision $e$ representing condition $f(x)=x$ in a numerical sense $\lvert f(x)-x \rvert  \leq e$

 * $x_{min}$  ... minimal x-axis value
 * $x_{max}$ ... maximal x-axis value
 * $y_{min}$ ... minimal y-axis value
 * $y_{max}$ ... maximal y-axis value

## Examples
For every example, function $f(x)$, precision $e=10^{-3}$ and starting point $x_0$ is defined. Points of scale can be seen in every image.
#### Example 1
$$f(x)=sqrt(1-x)=\sqrt{1-x}  \hspace{4em} x_0=0.2 $$

![example1][1]

#### Example 2
$$f(x)=sqrt(x)=\sqrt{x}  \hspace{4em} x_0=0.2 $$

![example2][2]

#### Example 3
$$f(x)=(-1/4) * x \\ \hat \\ \\ 3 +(3/4) * x+1/4=-\frac{1}{4} x^3+\frac{3}{4}x+\frac{1}{4} \hspace{4em} x_0=-1.3 $$

![example3][3]

#### Example 4 
$$f(x)=(-1/4) * x \\ \hat \\ \\ 3 +(3/4) * x+1/4=-\frac{1}{4} x^3+\frac{3}{4}x+\frac{1}{4} \hspace{4em} x_0=-2.45 $$

![example4][4]

[1]: https://live.staticflickr.com/65535/51943625931_d9d35277bf_c.jpg
[2]: https://live.staticflickr.com/65535/51942642957_760f90dbc2_c.jpg
[3]: https://live.staticflickr.com/65535/51944238145_1b225a4714_c.jpg
[4]: https://live.staticflickr.com/65535/51943948374_2ab0cc6f9c_c.jpg
[mathlink]: https://chrome.google.com/webstore/detail/mathjax-plugin-for-github/ioemnmodlmafdkllaclgeombjnmnbima/related
 





