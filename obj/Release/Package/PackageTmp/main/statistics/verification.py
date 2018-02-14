from main.statistics.cycle_divider import mean_pain_variance_by_cycle, getHpdays
from main.util.query import custom_sql
import statistics
from main.models import Tong2, VarianceInCycle
from scipy.stats import chi2
class Verification:
    def verify_by_pchisq(pi):
        hpdays=getHpdays(pi=pi)
        cycle=len(hpdays)
        if cycle > 1:
            cycle -=1 

        variances_with_null=[i[0] for i in custom_sql("select Var_cycle_{} from Var_Cycle".format(cycle))]
        variances=[]
        for var in variances_with_null:
            if var is not None:
                variances.append(var)
        sigma_cycle=float(statistics.mean(variances))
        tong2=Tong2.objects.filter(pi=pi).filter(n_attr_codeNM='통증 강도').filter(hospitalization_date=hpdays[cycle-1])
        pains=[]

        for pain in tong2:
            try:
                pains.append(int(pain.attr))
            except ValueError:
                pass
        sigma=statistics.variance(pains)
        n=len(pains)
        df=n-1
        v=df*sigma/sigma_cycle
        return sigma_cycle
        #return 1-chi2.cdf(v, df)