package ft;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "NdyAuth",
urlPatterns = { "/*" })
public class LoginCheckFilter implements Filter {

	@Override
	public void destroy() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("ログインチェック");
		// セッションが存在しない場合NULLを返す
		HttpSession session = ((HttpServletRequest)req).getSession(false);

		if(session != null){
			// セッションがNULLでなければ、通常どおりの遷移
			chain.doFilter(req, res);
		}else{
			// セッションがNullならば、ログイン画面へ飛ばす
			RequestDispatcher dispatcher = req.getRequestDispatcher("/Login.jsp");
			dispatcher.forward(req,res);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ

	}

}
